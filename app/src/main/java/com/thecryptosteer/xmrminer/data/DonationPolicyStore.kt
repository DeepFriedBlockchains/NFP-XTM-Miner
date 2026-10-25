package com.thecryptosteer.xmrminer.data

import android.content.Context

/** Compatibility facade: XTM-MINER never redirects mining rewards. */
class DonationPolicyStore(context: Context) {
    val currentBasisPoints: Int get() = 0
    val accountActiveUserNanos: Long get() = 0L
    fun update(accountNanos: Long, basisPoints: Int) = Unit
}
