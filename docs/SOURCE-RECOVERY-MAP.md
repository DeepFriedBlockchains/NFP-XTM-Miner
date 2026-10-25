# Source recovery map

Assessment date: 2026-10-25. **Classification B: partial source/evidence recovered; missing conversion reconstructed.** No original conversion Git object or complete matching source snapshot was recovered. This is not a claim of bit-for-bit source recovery.

| Input | Finding and use |
| --- | --- |
| GitHub `main` at `a38ddefd3571375dfac61736d9c99d2ba04bf60c` | Complete existing Android foundation; preserved as recovery parent |
| GitLab `main` at `a15913dcd8e0124e4de5cb5fd073cd377d47e224` | Ancestor of GitHub main; no unique divergent work; preserved |
| Original recorded base `4de0141f2774370de2e558959312111f7f30400b` | Still present in repository history; conversion edits reviewed against this foundation and current main |
| Recorded short conversion commit `6e739ed` | Historical identifier; original full commit/tree not recovered |
| `recorded-source-edits.json` | 35 historical edit commands; reviewed source-edit bodies used in order, with path remapping. Historical shell commands, uploads, toolchain mutations, commit/push commands and claimed test reports were not replayed |
| Reference APK | Preserved; identity, signature, library hashes, ZIP integrity checked; exact Tari artwork extracted |
| Recovery evidence ZIPs | Evidence only, not source archives; original reports retained as historical evidence |
| Donation-only patch | UI-only About card reused; supersedes earlier footer patch as described in supplied comparison |
| Independent October 19 Termux milestone | Not Android/JNI source; excluded from this project |

Retained: existing Android/JNI/native architecture, lifecycle and device safeguards, pool transport, pinned dependencies, licenses and original history. Reconstructed: Tari configuration, zero-fee enforcement, wallet/login handling, theme/navigation changes and subsequent corrections documented in historical edits. New work: Settings terminal entry, terminal viewer over existing logs, improved sanitization/concurrent retention, actual event logging, protocol regression fixtures, recovery documentation and byte-preserving source attributes.

The original internal Kotlin/JNI namespace remains `com.thecryptosteer.xmrminer`; renaming it would add unnecessary JNI risk. The installed application ID is the reference NFProjects ID. The Linux native source build remains mandatory; the reference `.so` is not substituted into the new build.

The local source folder is `NFP-XTM-Miner/` under the recovery-input folder. Its `origin` is GitHub and its `gitlab` remote is the independently verified GitLab repository. The recovery branch is `recovery/xtm-android-source` on both destinations when delivery verification is complete.
