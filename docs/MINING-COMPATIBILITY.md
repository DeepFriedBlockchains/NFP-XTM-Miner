# Tari mining compatibility

Scope: the existing ARM64 C++ RandomX engine with a pool providing standard `rx/0` work. This app does not implement a Tari full node, merge-mining proxy, block construction, SHA3x or Cuckaroo mining.

Kryptex's [Tari RandomX pool instructions](https://pool.kryptex.com/xtm-rx), checked 2026-10-25, publish `xmrig -a rx/0`, TCP `xtm-rx.kryptex.network:7038`, and `wallet/worker` login. This supports retaining the existing standard RandomX and Stratum architecture. Verify the provider's current TLS endpoint explicitly; no guessed TLS port is used. Compatibility with one documented pool protocol is not a blanket claim about all Tari pools or network proof-of-work lanes.

The production client advertises `rx/0`, rejects other explicitly provided algorithms, requires a 32-byte seed, validates compact/full share targets, checks nonce bounds and processes actual job blobs. Workers calculate real RandomX hashes and serialize four-byte nonces for share submission. Accepted counts require a correlated explicit success response; missing/null/ambiguous results are rejected. Production-client fixtures validate this parsing and serialization with an in-memory transport; they do not validate RandomX consensus or a real pool.

Tari mainnet Base58 validation checks split network/features encoding, one-sided support, envelope length and DammSum checksum. It is not a complete Ristretto public-key decoder or assurance of pool payout support. Source references: [Tari address implementation](https://github.com/tari-project/tari/blob/22886848eba3e67613e83e865b33103f1c8464fc/base_layer/common_types/src/tari_address/mod.rs), [DammSum](https://github.com/tari-project/tari/blob/22886848eba3e67613e83e865b33103f1c8464fc/base_layer/common_types/src/dammsum.rs), and [address RFC](https://rfc.tari.com/RFC-0155_TariAddress). Those pinned implementation files were read during recovery; BSD attribution is preserved.

Dependencies retain the existing pins: RandomX 2.0.1, libuv 1.52.1, hwloc 2.14.0, OpenSSL 3.5.7. Exact commits and SDK pins are in `DEPENDENCY_LOCK.json`. Android app minSdk is 36; native dependencies target API 35 as supported by pinned NDK r29. Existing 16 KiB ELF alignment and AArch64 branch relocation safeguards are retained.

Full RandomX has substantial memory requirements; light mode reduces memory at a large performance cost. Historical phone hashrates are not a benchmark for the new build. Real pool acceptance, reconnect behavior, TLS, device/JIT behavior and extended operation remain independent device gates in `TEST-REPORT.md`.
