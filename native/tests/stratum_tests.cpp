#include "cryptosteer/stratum_client.hpp"
#include <deque>
#include <iostream>
#include <stdexcept>
#include <string>
#include <vector>

// In-memory transport; the linked production client parses and serializes RPC.
// This is a protocol fixture, not a hashing or network acceptance test.
namespace {
std::deque<std::string> incoming;
std::vector<std::string> outgoing;
void check(bool value, char const* message) { if (!value) throw std::runtime_error(message); }
std::string job(std::string const& extra = "") {
    return "{\"job_id\":\"fixture\",\"blob\":\"" + std::string(152, '0') +
        "\",\"seed_hash\":\"" + std::string(64, '0') + "\",\"target\":\"ffffffff\"" + extra + "}";
}
}
namespace cryptosteer {
Transport::~Transport() { close(); }
void Transport::connect(Endpoint const&, int, bool) { fd_ = 1; }
void Transport::close() noexcept { fd_ = -1; }
void Transport::write_line(std::string_view line, int) { outgoing.emplace_back(line); }
bool Transport::read_line(std::string& line, int) {
    if (incoming.empty()) return false;
    line = incoming.front(); incoming.pop_front(); return true;
}
}
int main() {
    using namespace cryptosteer;
    Config config;
    config.password = "x";
    Endpoint endpoint;
    endpoint.host = "fixture.invalid";
    endpoint.port = 7038;
    incoming.push_back("{\"id\":1,\"result\":{\"id\":\"session\",\"status\":\"OK\",\"job\":" + job() + "}}");
    StratumClient client;
    check(client.connect_and_login(config, endpoint, "user-wallet/android", "android").has_value(), "login job missing");
    check(outgoing.back().find("user-wallet/android") != std::string::npos, "configured login changed");
    Share share;
    share.job_id = "fixture";
    share.nonce = 0x12345678U;
    share.result_hex = std::string(64, '0');
    const auto id = client.submit(share);
    check(outgoing.back().find("\"nonce\":\"78563412\"") != std::string::npos, "nonce must be little endian");
    incoming.push_back("{\"id\":" + std::to_string(id) + ",\"result\":{\"status\":\"OK\"}}");
    check(client.read_event(1).kind == PoolEventKind::Accepted, "explicit acceptance missing");
    for (auto const& result : {"null", "{}", "false"}) {
        const auto next = client.submit(share);
        incoming.push_back("{\"id\":" + std::to_string(next) + ",\"result\":" + result + "}");
        check(client.read_event(1).kind == PoolEventKind::Rejected, "ambiguous response counted accepted");
    }
    incoming.push_back("{\"id\":9999,\"result\":{\"status\":\"OK\"}}");
    check(client.read_event(1).kind != PoolEventKind::Accepted, "uncorrelated response counted accepted");
    for (auto const& extra : {",\"algo\":\"sha3x\"", ",\"nonce_offset\":-1", ",\"nonce_offset\":4294967335"}) {
        incoming.push_back("{\"method\":\"job\",\"params\":" + job(extra) + "}");
        bool rejected = false;
        try { (void)client.read_event(1); } catch (std::exception const&) { rejected = true; }
        check(rejected, "unsupported job accepted");
    }
    std::cout << "Production Stratum protocol fixtures: PASS\n";
}
