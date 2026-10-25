# File provenance audit

Original inputs are retained unchanged in the parent recovery folder or their attachment locations. Nothing was permanently deleted, and no independent Termux repository was modified. `validation/input-inventory.json` records exact filenames, byte sizes, SHA-256 hashes and classifications of every immediate input file. Paths in that inventory are relative to the original input folder, not the clean Android source folder.

| Original location or file | Classification | Disposition and rationale |
| --- | --- | --- |
| Parent `XTM-MINER-arm64-debug.apk`, `(1)`, `(2)`, `(3)` | Reference APK / duplicates | All four are 76,429,347 bytes with identical SHA-256. Preserved outside source; one analyzed |
| Parent `XTM-MINER-original-recovery-evidence.zip` and `(1).zip` | Relevant historical recovery evidence | Both contain reports and APK entry hashes, not Android source. Larger archive also contains the two UI draft patches |
| Parent `recorded-source-edits.json` | Partial Android source-edit evidence | Reviewed and reconstructed selected source operations; never treated as an executable instruction file or complete snapshot |
| Parent `NFProjects-donation-only.patch` | Relevant Android UI draft | Donation card reused in About; public support addresses stay confined to UI |
| Parent `NFProjects-footer.patch` | Superseded historical UI experiment | Retained outside active source; not applied wholesale |
| Parent `XMR to XTM Miner Prompt.txt` | Original conversion requirements | Read as guidance alongside latest master assignment |
| Parent `CONVERT XMR MINER TO TARI.txt` | Historical conversation/build claims | Read as evidence; claims not promoted to fresh validation |
| Parent screenshots/footer PNGs | UI/branding reference | Preserved; screenshots are evidence/reference, not buildable source |
| Attached `1000004580.png` | Historical runtime screenshot | Shows 541.0 H/s, FULL mode, 8 workers, 0 accepted shares and a 2:55 session at capture; cannot establish the later extended test by itself |
| Attached `1000004635(1).png` | Reference About screen | Confirms NFProjects debug ID/version and zero-fee display; also shows historical upstream footer |
| Attached `1000004453(1).png` | Original upstream UI | Comparison only; not the Tari application's runtime evidence |
| Attached `1000004636.png`, `1000004638.png` | Supplied footer artwork | Design reference; not original source or proof of a patched APK |
| Attached `tarilogo.png`, `xtmlogo.png` | Branding inputs | Available; exact artwork recovered from the reference APK is the selected binary baseline |
| Attached `REFERENCE-APK-COMPARISON.md` | Historical forensic report | Read and cross-checked against fresh APK metadata; not a fresh build result |
| Attached pasted master assignments, including latest `bbb3c6d6…/Pasted text.txt` | Recovery requirements | Latest request used with earlier prompts; duplicate variants are retained outside source |
| Referenced backup-drive prompt | Additional potential requirement copy | Access status recorded in recovery evidence; no destructive changes made to backup locations |
| Historical `xtm-miner-source-milestone.zip` / Termux materials | Separate project | Evidence inventory identifies Termux/XMRig with no APK/JNI integration; excluded, not deleted |

No unexplained binary was adopted as a native dependency. Vendored dependencies are retained from Git history with their existing pins and RandomX relocation patch. Build-generated dependencies, caches, signing material, APKs and machine-local configuration are excluded from source commits.
