package com.thecryptosteer.xmrminer.domain

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class LogSanitizerTest {
    @Test fun redactsStructuredSecretsAndCredentialUris() {
        val source = "{\"password\":\"two words\", token=abc123, seed phrase='one two three'} stratum+tcp://user:pw@pool:7038"
        val safe = LogSanitizer.sanitize(source)
        listOf("two words", "abc123", "one two three", "user:pw").forEach { assertFalse(safe.contains(it)) }
        assertTrue(safe.contains("pool:7038"))
    }

    @Test fun redactsTariAddressesAndPreservesRealEventMeaning() {
        val address = "128bJEqBH2yCPKdbbkQxXFjCwGjEhg7pGKYqeGR548mxdPrgaSfHoJ6Gw12nMskpRjKR6uneeJBED8gSVxSJ3Pxfw3L"
        val safe = LogSanitizer.sanitize("Accepted share for $address/android; 510 H/s")
        assertFalse(safe.contains(address))
        assertTrue(safe.contains("Accepted share"))
        assertTrue(safe.contains("510 H/s"))
    }

    @Test fun boundsMessagesStripsTerminalControlsAndIsIdempotent() {
        val safe = LogSanitizer.sanitize("\u001b[31mERROR password=secret value\n" + "x ".repeat(10_000))
        assertTrue(safe.length <= 2_000)
        assertFalse(safe.contains('\u001b'))
        assertFalse(safe.contains("secret value"))
        assertEquals(safe, LogSanitizer.sanitize(safe))
    }
}
