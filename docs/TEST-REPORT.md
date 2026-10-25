# Recovery validation report

Status: recovery source under active validation, 2026-10-25. Exact receipts and final build identity will be appended after fresh compilation. This report does not claim release readiness.

| Level | Current evidence |
| --- | --- |
| 1. Source recovered/reconstructed | Partial evidence recovery; converted Android source reconstructed on existing foundation |
| 2. Android compilation | Pending fresh Gradle/native build |
| 3. Automated tests | Native host and production-client protocol fixtures PASS; RandomX patch, mock-pool and source-archive policy PASS; source/lock checks PASS; Android JVM/lint pending |
| 4. New APK installed/launched | Not performed |
| 5. New APK real Tari pool connection | Not performed |
| 6. New APK real jobs processed | Not performed |
| 7. New APK accepted real shares | Not performed |
| 8. Extended physical-device test | Not performed |

Protocol tests use an in-memory transport linked to production `stratum_client.cpp`: login preservation, little-endian nonce serialization, explicit acceptance, ambiguous/null rejection, uncorrelated response handling, unsupported algorithm and malformed nonce rejection. Mock-pool tests are separate local fixtures. Neither proves real accepted RandomX shares.

Added Android tests cover Tari wallet/checksum behavior, distribution zero-fee policy, secret/address redaction, bounded messages, concurrent 500-entry retention and clearing/restarting display. Actual results must be recorded from the new Gradle test XML, not inferred from source.

The original APK's ZIP integrity, identity, certificate and native hashes were freshly checked. Historical reports of 23 tests, lint 0 errors/37 warnings and extended pool testing refer to the older APK only. The new terminal/device behavior and native mining output remain release gates until independently exercised.
