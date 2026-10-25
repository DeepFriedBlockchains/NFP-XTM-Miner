package com.thecryptosteer.xmrminer

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test

class DistributionPolicyTest {
    @Test fun forkNeverEnablesUpstreamServicesOrDeveloperMining() {
        assertFalse(BuildConfig.SELF_UPDATE_ENABLED)
        assertFalse(BuildConfig.INSTALLATION_TELEMETRY_ENABLED)
        assertEquals(0, BuildConfig.DONATION_BASIS_POINTS)
        assertEquals(0, BuildConfig.REDUCED_DONATION_BASIS_POINTS)
        assertEquals("", BuildConfig.DONATION_WALLET)
        assertEquals("", BuildConfig.DONATION_POOL_HOST)
        assertEquals(0, BuildConfig.DONATION_POOL_PORT)
    }
}
