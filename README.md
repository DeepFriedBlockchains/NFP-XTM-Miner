# NFP-XTM-Miner

**XTM-MINER** is the NFProjects Android ARM64 Tari miner, developed by Kasual Labz from the existing CryptoSteer Android application. This recovery preserves the Compose UI, C++ RandomX/JNI engine, worker controls, Android foreground service, encrypted settings, and battery/thermal protections.

**Recovery build under validation.** The historical tested APK is preserved separately. Its device results do not validate this reconstructed source or a new APK. See [test report](docs/TEST-REPORT.md) and [release checklist](docs/RELEASE-CHECKLIST.md).

- Tari `rx/0` pool configuration and mainnet Base58 envelope/checksum validation.
- Reference Tari launcher/header artwork and purple-to-pink dashboard.
- **0% application developer mining fee**; developer-wallet connection rotation is removed. Provider pool fees are separate.
- Settings → **Mining Terminal / Live Logs** reuses the native/service event stream, with timestamps, severity colors, auto-scroll, filtering, bounded retention, copy, and sanitized diagnostic export.
- Voluntary XTM/XMR donation information is confined to About; it is never used as the mining payout configuration.
- Upstream accounts, remote control, installation telemetry and private updater are disabled.

Primary source: [GitHub](https://github.com/DeepFriedBlockchains/NFP-XTM-Miner). Secondary backup: [GitLab](https://gitlab.com/DeepFriedBlockchains/NFP-XTM-Miner). Recovery branch: `recovery/xtm-android-source`. Main branches and upstream history are preserved.

## Build and test

Use the pinned Linux build environment in [build instructions](docs/BUILD-INSTRUCTIONS.md). Installation requires Android API 36 or newer and ARM64. The application ID remains `com.nfprojects.xtmminer` (`com.nfprojects.xtmminer.debug` for debug), matching the reference APK's identity.

```sh
git clone --branch recovery/xtm-android-source https://github.com/DeepFriedBlockchains/NFP-XTM-Miner.git
cd NFP-XTM-Miner
export ANDROID_SDK_ROOT="$HOME/Android/Sdk"
bash scripts/setup-linux-builder.sh
bash scripts/build-native-dependencies.sh
bash gradlew --no-daemon --no-configuration-cache :app:testDebugUnitTest :app:lintDebug :app:assembleDebug :app:assembleDebugAndroidTest
```

No wallet private key, mnemonic, or recovery phrase is needed. Configure your own public Tari payout address and a currently documented compatible pool endpoint. See [mining compatibility](docs/MINING-COMPATIBILITY.md) and [physical-device test instructions](docs/PHONE-TESTING.md).

## Recovery evidence

[Source map](docs/SOURCE-RECOVERY-MAP.md), [file provenance](docs/FILE-PROVENANCE-AUDIT.md), [evidence](docs/RECOVERY-EVIDENCE.md), [APK comparison](docs/APK-COMPARISON.md), and [developer-fee audit](docs/DEVELOPER-FEE-AUDIT.md) distinguish recovered artifacts, reconstructed code, fresh checks and outstanding device validation.

The independent Termux/XMRig project is not part of this Android source tree. Original inputs and APKs remain outside the clean project and are not modified.

Original application code is Apache-2.0, copyright 2026 THE CRYPTO STEER LLC. Upstream artwork and third-party dependencies retain their own licenses. Tari checksum attribution is in `LICENSES/Tari-BSD-3-Clause.txt`. See `LICENSE`, `NOTICE`, `LICENSES/` and `ARTWORK_LICENSE.md`. NFProjects is not affiliated with Tari or the upstream developer.
