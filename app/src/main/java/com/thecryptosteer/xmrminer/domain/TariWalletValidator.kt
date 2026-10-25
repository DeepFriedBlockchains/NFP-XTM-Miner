package com.thecryptosteer.xmrminer.domain

import java.math.BigInteger

/** Mainnet Base58 envelope and DammSum validation. Pool verifies key usability/payout support. */
object TariWalletValidator {
    private const val alphabet = "123456789ABCDEFGHJKLMNPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz"

    fun isValid(address: String): Boolean = runCatching {
        require(address.length in 45..443 && address.all { it in alphabet })
        // Tari separately encodes network, feature byte, and key/checksum payload.
        val network = alphabet.indexOf(address[0])
        val features = alphabet.indexOf(address[1])
        require(network == 0 && features in 1..7 && (features and 1) != 0)
        val tail = decode(address.substring(2))
        val bytes = byteArrayOf(network.toByte(), features.toByte()) + tail
        require(bytes.size == 35 || bytes.size in 67..323)
        val memo = if (bytes.size == 35) 0 else bytes.size - 67
        require(if (bytes.size == 35) (features and 4) == 0 else memo == 0 || (features and 4) != 0)
        var checksum = 0
        bytes.forEach {
            checksum = checksum xor (it.toInt() and 255)
            checksum = ((checksum shl 1) and 255) xor (if ((checksum and 128) != 0) 0x1b else 0)
        }
        require(checksum == 0)
        true
    }.getOrDefault(false)

    private fun decode(value: String): ByteArray {
        var number = BigInteger.ZERO
        value.forEach { number = number.multiply(BigInteger.valueOf(58)).add(BigInteger.valueOf(alphabet.indexOf(it).toLong())) }
        val magnitude = if (number == BigInteger.ZERO) byteArrayOf() else number.toByteArray().let { if (it[0] == 0.toByte()) it.drop(1).toByteArray() else it }
        return ByteArray(value.takeWhile { it == '1' }.length) + magnitude
    }
}

/** Kryptex documents wallet/worker; other compatible pools receive wallet plus rigid. */
fun poolLogin(profile: PoolProfile): String = if (
    (profile.primary.host == "xtm-rx.kryptex.network" ||
        Regex("^xtm-rx-[a-z]+\\.kryptex\\.network$").matches(profile.primary.host)) && profile.worker.isNotBlank()
) "${profile.username}/${profile.worker}" else profile.username
