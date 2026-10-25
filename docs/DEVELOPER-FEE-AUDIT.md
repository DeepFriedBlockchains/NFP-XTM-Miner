# Developer mining fee audit

Required policy: **0% application developer mining**. Pool fees and voluntary donations are separate.

Source inspection of the recovery branch found:

| Boundary | Enforcement |
| --- | --- |
| Gradle and dependency lock | Primary/reduced basis points zero; developer wallet/host/worker empty; developer pool port zero |
| Managed config JSON | Fee always encoded as zero |
| Native config parser | Rejects nonzero `donationBasisPoints`; clears developer wallet; native default zero |
| Native control loop | Selects only configured user endpoints and `config_.username`; developer-endpoint helper, developer-wallet constant and timed connection switch removed |
| Native fee-update API | Permits only zero |
| Managed historical account policy | Compatibility facade returns zero regardless of old stored tier |
| Upstream cloud | Shared transport rejects upstream service requests; updater/telemetry flags disabled |
| About support card | User-requested public XTM/XMR addresses, copy buttons only; not passed to mining config |

The generic historical scheduler still includes its legacy positive-fee arithmetic for compatibility tests. Its presence is not an active payout path: production configuration and updates cannot select those rates, and the network rotation path is removed. Zero-fee regression checks prove no developer time becomes due and no donated time accumulates for a zero scheduler.

Production protocol fixtures preserve the supplied user login and require correlated explicit accepted responses. Native/JVM compilation and tests must still pass, and a physical network capture/extended session must confirm no unexpected wallet or endpoint switch in the new APK. Static source inspection is not an exhaustive dynamic audit of every third-party dependency.

The voluntary support addresses in `AboutDonationCard.kt` are public donation UI data explicitly supplied for this project. They are not defaults in pool profiles or `DEPENDENCY_LOCK.json`. Never enter a seed/private key. Required upstream copyright/license notices remain intact.
