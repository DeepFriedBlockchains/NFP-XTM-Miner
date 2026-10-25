# Recovery evidence

Fresh checks on 2026-10-25:

- GitHub repository identity/access verified through the connected account; one published branch (`main`), no tags and no PRs found at investigation time.
- GitHub releases and Actions run collections are empty. The artifact collection endpoint was rejected by the connector; with zero runs, no run artifact was available to inspect. This is not a claim that provider-side backups do not exist.
- GitLab project ID `88018934`, namespace `DeepFriedBlockchains/NFP-XTM-Miner`, URL verified through connected GitLab. Its only branch is `main`; no releases or pipelines found. Git fetch confirms GitLab main is an ancestor of GitHub main.
- GitHub base and history cloned locally without rewriting either remote. Historical conversion base object `4de0141f2774370de2e558959312111f7f30400b` exists.
- All four supplied APKs freshly SHA-256 checked and identical. Reference ZIP CRC verification passed, all three native library hashes recorded, and embedded AGP metadata is `9.3.0`.
- Reference package, version, permissions, ARM64 architecture and signature freshly inspected using Android SDK Build Tools 36.0.0. Signer certificate SHA-256 is `5667196a8b427373214a3bf7d08534777f4381bb2bf3495336a9f2f0ab9611d5`.
- The supplied evidence archives contain reports and inventories, not the original source tree or Git object database. Historical recovery records report the original cloud project folder missing; this work did not repeatedly search expired cloud filesystems.
- Recent turns in the original conversion chat and the later footer-recovery chat were read. They confirm APK delivery, unfinished source delivery, and the origin of the 35-command evidence file. No messages were sent to those chats.

Historical statements about 23 Android tests, lint with 37 warnings, 16 in-app accepted shares, 21 pool-side shares and extended Pixel testing are historical reports only. The provided 541 H/s screenshot has zero accepted shares at that moment. None of these establishes a new APK's mining validity.

Fresh checks and remaining gaps are recorded separately in `TEST-REPORT.md`. Detailed input hashes, native library hashes and APK-entry hashes are in `validation/`. The source reconstruction deliberately does not import historical generated validation reports as new receipts.
