# Release gates

- [x] Verify official GitHub/GitLab destinations and preserve main/history.
- [x] Identify and preserve identical reference APK copies and fresh checksum/signature metadata.
- [x] Classify partial source recovery and exclude independent Termux work without deleting inputs.
- [x] Reconstruct Tari conversion on the existing Android foundation; recover reference artwork.
- [x] Source zero-fee enforcement and UI-only voluntary donation separation.
- [x] Implement Settings terminal over existing logs with sanitization and bounded retention.
- [x] Fresh ARM64 native build and full Android compilation.
- [x] Fresh tests/lint, APK identity, signature and 16 KiB verification (27 tests; 0 lint errors / 47 warnings).
- [x] Compare APK resources/native interfaces; record expected differences and remaining device UI comparison.
- [ ] Preserve validated recovery source on GitHub, open review PR, verify same commits on GitLab.
- [ ] Test install/launch with an explicit signer/data-preservation plan.
- [ ] Verify real jobs, accepted pool shares and one-hour physical-device stability from the new APK.
- [ ] Verify terminal export, controls, battery/thermal behavior, background service and accessibility.
- [ ] Complete dependency/license/branding distribution review and resolve material lint/runtime issues.
- [ ] Establish maintainer-controlled production signing/update policy and independently validate release build.
- [ ] Obtain explicit owner approval before publishing a public release. No automatic merge or Release publication.

Until these gates pass, any compiled artifact is a debug test candidate, not a production release. Use `NFP-XTM-Miner-arm64-release.apk` only for a verified signed release build.
