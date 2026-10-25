# Reproducible Android build

Use x86-64 Linux (including WSL), JDK 17, and a path without spaces for the toolchain when practical. Install host build prerequisites listed in historical `BUILDING.md`: C/C++ compiler, make, Perl, autoconf, automake, libtool, pkg-config, CMake/Ninja, curl, unzip, Python 3 and jq. No release keystore is needed for a debug build.

Pins retained: Gradle 9.5.0, AGP 9.3.0, Kotlin 2.4.10, SDK 37, Build Tools 37.0.0, NDK 29.0.14206865, CMake 3.22.1. Bootstrap distributions are checksum checked by the existing scripts. The Gradle dependency list is retained; this does not promise bit-identical signed APK bytes across independently generated debug keys.

```sh
git clone --branch recovery/xtm-android-source https://github.com/DeepFriedBlockchains/NFP-XTM-Miner.git
cd NFP-XTM-Miner
export JAVA_HOME=/path/to/jdk-17
export PATH="$JAVA_HOME/bin:$PATH"
export ANDROID_SDK_ROOT=/path/to/android-sdk
bash scripts/setup-linux-builder.sh
python3 build-logic/verify_lock.py
python3 build-logic/source_quality_check.py
bash scripts/run-native-tests.sh
python3 tests/native/test_randomx_aarch64_branch_patch.py
python3 tests/integration/test_mock_stratum.py
python3 tests/integration/test_source_archive_policy.py
bash scripts/build-native-dependencies.sh
bash gradlew --no-daemon --no-configuration-cache --max-workers=4 :app:testDebugUnitTest :app:lintDebug :app:assembleDebug :app:assembleDebugAndroidTest
```

Vendored source is already included. Do not run the historical vendor-clone helper over this vendored-without-VCS tree: it assumes separate dependency Git repositories. Existing dependency source pins and the checked-in RandomX patch must be verified before replacing dependencies. The native dependency build recreates only generated `third_party/build` and `third_party/install`; do not place user material there.

Output: `app/build/outputs/apk/debug/app-debug.apk`. Copy verified bytes to `NFP-XTM-Miner-arm64-debug.apk`, compute SHA-256, and record the new certificate with `apksigner verify --verbose --print-certs`. Check `aapt2 dump badging`, `zipalign -c -P 16 -v 4`, native ELF alignment and archive ARM64 contents. Save test XML and lint receipts; compiling the Android test APK does not execute instrumentation.

Debug signing keys are development identities. If they differ from the reference, Android cannot install the new APK as an in-place update. Preserve the installed known-good app/data until the owner chooses a test installation strategy. Never commit or overwrite signing keys. Production signing and public Release publication are separate, explicit release gates.
