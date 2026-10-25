package com.thecryptosteer.xmrminer.domain

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class TariWalletValidatorTest {
    // Public-key fixture from Tari address tests, re-encoded for mainnet. Never used for mining.
    private val address = "14AxNmpvVwr4sYxUPhYXnXmcCDsMxu54ZfBjb9EDpV5JBYC2u96kqoXJ99sxsytX8dgp8KVZxJNcanFsPqZSNCvd7Xg"

    @Test fun validatesMainnetEnvelopeAndDetectsTranscriptionErrors() {
        assertTrue(TariWalletValidator.isValid(address))
        address.indices.forEach { index ->
            val replacement = if (address[index] == '2') '3' else '2'
            val changed = address.replaceRange(index, index + 1, replacement.toString())
            assertFalse("mutation at $index", TariWalletValidator.isValid(changed))
        }
        assertFalse(TariWalletValidator.isValid(""))
        assertFalse(TariWalletValidator.isValid("monero-wallet"))
        assertFalse(TariWalletValidator.isValid(address + "/worker"))
    }

    @Test fun kryptexLoginPreservesWalletAndOptionalWorker() {
        val profile = PoolProfile(name = "Tari", primary = PoolEndpoint("xtm-rx.kryptex.network", 7038, false), username = address)
        assertEquals(address, poolLogin(profile))
        assertEquals("$address/android", poolLogin(profile.copy(worker = "android")))
        assertFalse(PoolValidator.validate(profile.copy(worker = "bad/worker")).valid)
    }
}
