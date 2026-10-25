# Recovery delivery

Recovery classification B: the original converted source snapshot was not found. The existing Android foundation and 35-command historical evidence supported reconstruction; no unrelated application or Termux repository was created or modified.

Source directory: `NFP-XTM-Miner/` under the original input folder. Retained: Kotlin/Compose/JNI, native RandomX workers/transport, Android lifecycle safeguards, encrypted settings, licenses, pinned dependencies and history. Reconstructed: Tari conversion, login/validation, zero-fee enforcement and reference branding. New: Settings terminal and safe log export. Exclusions and exact input hashes are in `FILE-PROVENANCE-AUDIT.md` and `validation/input-inventory.json`.

Primary: [GitHub recovery branch](https://github.com/DeepFriedBlockchains/NFP-XTM-Miner/tree/recovery/xtm-android-source), [draft PR #1](https://github.com/DeepFriedBlockchains/NFP-XTM-Miner/pull/1). Compiled source checkpoint `4ad51e9`; later report commits preserve the same application inputs. Main remains untouched.

Secondary: [verified GitLab project](https://gitlab.com/DeepFriedBlockchains/NFP-XTM-Miner). The exact-commit backup push requires local Git Credential Manager authentication; check the final handoff for the delivered remote SHA. Do not force-push or replace GitLab main. Once authenticated, push `recovery/xtm-android-source` and compare remote head SHAs.

Debug APK: `NFP-XTM-Miner-arm64-debug.apk`, SHA-256 `328a44c5f25341243725e990fe695e4a9f278e2189300e680a32baf52949800c`, 75,068,158 bytes. Original reference APK remains unchanged, SHA-256 `ded5a9e67f0203ead217fd7cd94a39c95719b2a5d97f7284fa7648f45f0f003b`. New debug signer differs; this cannot be installed over the reference as a signed update.

Fresh results: Android and native compilation PASS, 27 JVM tests PASS, lint 0 errors / 47 warnings, protocol/native/archive checks PASS, APK signature/CRC/ARM64/16 KiB checks PASS. Detailed receipts are checked in under `validation/`. Android instrumentation was compiled but not executed. No new-device install, live pool connection, real jobs/accepted shares or extended physical test was performed.

Settings → Mining Terminal / Live Logs uses actual native/service events, timestamps and severity colors, auto-scroll/filter/copy/clear and sanitized export, with a 500-entry retention bound. About includes the supplied XTM and XMR voluntary addresses and separate copy buttons. Developer mining is source-enforced at 0%; voluntary support addresses never enter mining configuration. Source audit is in `DEVELOPER-FEE-AUDIT.md`; dynamic network validation remains a device gate.

Before public release: complete owner-selected test installation/signing strategy, run `PHONE-TESTING.md`, verify real accepted shares and extended stability, inspect terminal/UI/accessibility and Android safeguards, review lint/dependency/license/branding terms, establish maintainer-controlled release signing, independently verify the signed release build, and obtain explicit publication approval. No merge or public Release was performed.
