package com.thecryptosteer.xmrminer.data

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Test

class MiningLogRetentionTest {
    @Test fun concurrentNativeEventsStayBoundedAndClearDoesNotStopFutureEvents() {
        MiningStateBus.clearLogs()
        val writers = (1..4).map { worker ->
            Thread { repeat(200) { MiningStateBus.appendLog("INFO", "Worker $worker event $it password=hidden") } }
        }
        writers.forEach { it.start() }
        writers.forEach { it.join() }
        val logs = MiningStateBus.logs.value
        assertEquals(500, logs.size)
        assertEquals(500, logs.map { it.message }.toSet().size)
        assertFalse(logs.any { it.message.contains("hidden") })
        MiningStateBus.clearLogs()
        assertEquals(0, MiningStateBus.logs.value.size)
        MiningStateBus.appendLog("SUCCESS", "Pool accepted share")
        assertEquals("Pool accepted share", MiningStateBus.logs.value.single().message)
        MiningStateBus.clearLogs()
    }
}
