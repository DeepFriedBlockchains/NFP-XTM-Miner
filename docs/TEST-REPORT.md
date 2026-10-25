# Recovery validation report

Status: fresh ARM64 debug build validated on 2026-10-25. This is a debug testing candidate; physical-device and real-pool validation remain outstanding.

| Level | Current evidence |
| --- | --- |
| 1. Source recovered/reconstructed | Partial evidence recovery; converted Android source reconstructed on existing foundation |
| 2. Android compilation | PASS: native ARM64 engine, debug APK and instrumentation-test APK compiled |
| 3. Automated tests | 27 JVM tests PASS, 0 failures/errors/skipped. Lint: 0 errors, 47 warnings. Native/protocol, source/lock, RandomX patch, mock-pool, archive-policy and Git-archive marker checks PASS |
| 4. New APK installed/launched | Not performed |
| 5. New APK real Tari pool connection | Not performed |
| 6. New APK real jobs processed | Not performed |
| 7. New APK accepted real shares | Not performed |
| 8. Extended physical-device test | Not performed |

Protocol tests use an in-memory transport linked to production `stratum_client.cpp`: login preservation, little-endian nonce serialization, explicit acceptance, ambiguous/null rejection, uncorrelated response handling, unsupported algorithm and malformed nonce rejection. Mock-pool tests are separate local fixtures. Neither proves real accepted RandomX shares.

Android tests cover Tari wallet/checksum behavior, distribution zero-fee policy, secret/address redaction, bounded messages, concurrent 500-entry retention and clearing/restarting display. Actual test XML is in `validation/android-unit-tests/`. Instrumentation was compiled, not executed.

The original APK's ZIP integrity, identity, certificate and native hashes were freshly checked. Historical reports of 23 tests, lint 0 errors/37 warnings and extended pool testing refer to the older APK only. The new terminal/device behavior and native mining output remain release gates until independently exercised.

## Delivered debug APK and fresh receipts

- `NFP-XTM-Miner-arm64-debug.apk`, 75,068,158 bytes.
- SHA-256: `328a44c5f25341243725e990fe695e4a9f278e2189300e680a32baf52949800c`.
- Compiled checkpoint: `4ad51e9`; runtime code from `7c3033e`, with the archive-marker correction included before final packaging. Later report-only commits do not change app code.
- Package `com.nfprojects.xtmminer.debug`, version `2.2.1-debug`, code `20201`, ARM64, minSdk 36 / targetSdk 37.
- Signature verified. New debug certificate SHA-256 `cdda1c58c234456cb60cc89b6bb437f75b0e96b64ebfe1a365ac1ff09defcf56` differs from the reference. **An in-place signed update is not possible.** Preserve the known-good installation/data; choose the test installation plan in `PHONE-TESTING.md`.
- ZIP CRC, APK 16 KiB alignment and all native ELF LOAD alignments PASS; every native library is AArch64.
- Reference Tari logo pixels match exactly. Both supplied voluntary support addresses, terminal label and eight license/notice assets are present.
- Original upstream mining wallet and MoneroOcean endpoint absent from DEX/native code. Generated BuildConfig confirms zero fees, empty developer destinations, disabled upstream services and a debug-testing label.

Final Gradle tasks `:app:testDebugUnitTest :app:lintDebug :app:assembleDebug :app:assembleDebugAndroidTest` exited 0, BUILD SUCCESSFUL. Used `--no-daemon --no-configuration-cache --max-workers=1 -Dorg.gradle.jvmargs=-Xmx1536m -Pkotlin.compiler.execution.strategy=in-process -Pkotlin.incremental=false` for the small Linux environment. A first higher-memory attempt exhausted memory; it is not counted as a completed build pass. The fresh XML records 27 passing tests; unchanged tests were up to date on the successful retry.

Native dependencies compiled from pinned source. OpenSSL's inherited hidden-file archive rule initially omitted the provenance marker. The explicit archive exception and regression check corrected this; dependency verification then passed and the ARM64 engine linked. No reference `.so` was inserted into the new APK.

Lint's 47 warnings cover dependency/tool updates, icon formats, Kotlin convenience APIs, an existing optional TLS trust manager, backup declarations, Chrome OS ABI coverage, a modifier parameter, an empty superclass call and a preferences update. Review before release; dependency versions were preserved.

Receipts: `validation/android-unit-tests/`, `lint-results-debug.xml`, `android-build.txt`, `arm64-engine-build.txt`, `native-dependency-verification.txt`, `native-and-protocol-tests.txt`, `generated-build-config.txt` and `new-apk-*`. No connected device was available.
