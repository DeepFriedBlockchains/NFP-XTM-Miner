package com.thecryptosteer.xmrminer.domain

/** Shared display/export boundary. Mining never needs private wallet material. */
object LogSanitizer {
    private val control = Regex("[\\x00-\\x08\\x0B-\\x1F\\x7F]")
    private val credentials = Regex("(?i)([a-z][a-z0-9+.-]*://)[^\\s/@]+:[^\\s/@]*@")
    private val secret = Regex("(?i)([\\\"']?(?:password|pass|token|secret|authorization|api[_ -]?key|private[_ -]?key|recovery[_ -]?phrase|seed[_ -]?phrase|mnemonic)[\\\"']?\\s*[:=]\\s*)(?:\\\"(?:\\\\.|[^\\\"\\\\])*\\\"|'[^']*'|[^\\r\\n,;}]+)")
    private val encoded = Regex("(?<![A-Za-z0-9])[1-9A-HJ-NP-Za-km-z]{45,443}(?![A-Za-z0-9])|(?<![A-Za-z0-9])[0-9a-fA-F]{64,}(?![A-Za-z0-9])")

    fun sanitize(value: String): String = value.take(16_384)
        .replace(control, "")
        .replace(credentials, "$1<credentials-redacted>@")
        .replace(secret) { "${it.groupValues[1]}<redacted>" }
        .replace(encoded, "<address-or-key-redacted>")
        .take(2_000)
}
