package com.thecryptosteer.xmrminer.domain

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class PoolValidatorTest {
    @Test
    fun acceptsTlsPoolWalletAndWorker() {
        val result = PoolValidator.validate(
            PoolProfile(
                name = "Primary",
                primary = PoolEndpoint("pool.example.com", 443, true),
                username = "14AxNmpvVwr4sYxUPhYXnXmcCDsMxu54ZfBjb9EDpV5JBYC2u96kqoXJ99sxsytX8dgp8KVZxJNcanFsPqZSNCvd7Xg",
                worker = "Pixel10-Max",
            ),
        )
        assertTrue(result.errors.joinToString(), result.valid)
    }

    @Test
    fun acceptsOptionalWorkerName() {
        val result = PoolValidator.validate(
            PoolProfile(
                name = "Missing worker",
                primary = PoolEndpoint("pool.example.com", 443, true),
                username = "14AxNmpvVwr4sYxUPhYXnXmcCDsMxu54ZfBjb9EDpV5JBYC2u96kqoXJ99sxsytX8dgp8KVZxJNcanFsPqZSNCvd7Xg",
                worker = "",
            ),
        )
        assertTrue(result.errors.joinToString(), result.valid)
    }

    @Test
    fun rejectsInvalidPortAndHost() {
        val result = PoolValidator.validate(
            PoolProfile(
                name = "Bad",
                primary = PoolEndpoint("", 70000),
                username = "x",
                worker = "worker",
            ),
        )
        assertFalse(result.valid)
    }
}
