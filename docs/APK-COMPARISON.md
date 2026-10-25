# APK and source comparison

Reference: `XTM-MINER-arm64-debug.apk`, SHA-256 `ded5a9e67f0203ead217fd7cd94a39c95719b2a5d97f7284fa7648f45f0f003b`, 76,429,347 bytes. All four local copies are identical. No distinct later branded APK was found among supplied APKs; patches are source drafts, not binary evidence. The owner identifies this as the tested build; no device-side APK checksum ties an individual historical test session to it independently.

| Property | Historical upstream/current-main foundation | Reference APK | Recovered branch target |
| --- | --- | --- | --- |
| Debug installed ID | `com.thecryptosteer.xmrminer.debug` | `com.nfprojects.xtmminer.debug` | Preserve reference ID |
| Version | `2.2.1-debug`, code `20201` | Same | Preserve, identify new bytes by checksum |
| Min/target SDK | 36 / 37 | 36 / 37 | Preserve |
| Label and theme | Upstream XMR branding/orange | XTM-MINER, purple/pink | Reference theme and extracted Tari artwork |
| App developer mining | 200 / 100 basis-point configuration | Historical report found compiled zero policy/configuration | Zero managed configuration; native developer connection path removed and nonzero fee configuration rejected |
| Upstream services | Enabled in foundation debug defaults | Historical report found disabled flags | Disabled flags and rejecting shared transport |
| Native integration | C++ RandomX and JNI | ARM64 `libcryptosteer_miner.so` | Build from reconstructed source, never copy the reference binary as new source output |
| Settings logs | Existing Logs page | Existing Logs navigation | Settings terminal entry plus same event stream, bounded retention and sanitized export |
| About donations | Upstream fee disclosure | Zero developer fee; original footer | UI-only voluntary support, Kasual Labz/NFProjects credits, required attribution retained |

Fresh signature verification passed (APK v2). The reference signer is an Android debug certificate; the original private signing key is not present in supplied recovery material. A newly generated debug key must not be described as update-compatible with the installed reference.

Reference native libraries:

- `libcryptosteer_miner.so`: 6,147,632 bytes, SHA-256 `a6ef21054a1bb3753fe42580a73292a952a1642ebe2538de158dfc14cd44c868`.
- `libandroidx.graphics.path.so`: 10,096 bytes, SHA-256 `41e9a793c43a0f4fddb19e33f346bace464f30f888ba7b9eaf96294ea115bfb6`.
- `libdatastore_shared_counter.so`: 7,784 bytes, SHA-256 `178f89a2c6c9234ddf980dd4cd1f3616186a46b2b932aafa6707abd04c5fd76a`.

All three have ELF machine 183 (AArch64). Reference entry hashes and metadata are retained in `validation/`. Manifest inspection reports a visible special-use mining foreground service, notification/network/wake-lock permissions, biometric access, legacy updater permission and disabled upstream service declarations. These declarations are not evidence that remote-control or updater networking is enabled in this fork.

A fresh APK/native build is expected to differ after adding the terminal and rebuilding with a new debug signing key. Binary equality is not the acceptance criterion. UI equivalence and actual accepted shares require independent device testing; see `PHONE-TESTING.md`.

## Fresh rebuilt APK

`NFP-XTM-Miner-arm64-debug.apk`: 75,068,158 bytes, SHA-256 `328a44c5f25341243725e990fe695e4a9f278e2189300e680a32baf52949800c`. Signature, CRC and 16 KiB packaging/ELF alignment PASS. ID, version, SDK bounds, launcher and ARM64 match. Logo pixels match exactly. Both support addresses, terminal label and eight license assets are packaged.

AndroidX graphics and DataStore native hashes match the reference exactly. Rebuilt engine: 6,150,192 bytes, SHA-256 `a731d0a0fef99b2329d3d30b7928bffd34ba10727d2a2e03efc213a2cb5f14e8`. Engine bytes differ after reconstruction/logging. Device UI equivalence and real mining remain unverified.

New certificate SHA-256 `cdda1c58c234456cb60cc89b6bb437f75b0e96b64ebfe1a365ac1ff09defcf56` differs from reference `5667196a8b427373214a3bf7d08534777f4381bb2bf3495336a9f2f0ab9611d5`. This APK cannot be an in-place signed update. Fresh receipts are in `validation/new-apk-*`.
