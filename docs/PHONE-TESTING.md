# New APK physical-device validation

Record exact APK filename/SHA-256, signer fingerprint, device, Android version, thread/memory mode, pool endpoint and session timestamps. Test only a device you control and your own public Tari payout address. Do not use fixture/support addresses for mining.

1. Preserve the original APK and installed data. Compare signing certificates before trying an update. If Android reports a signature conflict, do not uninstall the known-good app automatically; choose a separate test device/profile or an owner-approved data/installation plan.
2. Confirm XTM-MINER/TARI NETWORK, original dashboard layout, Tari icon, purple/pink accents, About credits/donations and zero developer fee. Check Pixel 9a-sized layout, rotation and enlarged fonts.
3. Save your own valid mainnet Base58 address and currently documented `rx/0` pool. Reject blank/Monero/invalid-checksum addresses. Reopen settings to confirm persistence. Never enter private keys or mnemonics.
4. Confirm notification permission and visible mining foreground notification. Begin with conservative worker, battery and thermal settings; use light mode if memory is constrained. Do not deliberately overheat the device.
5. Start and distinguish TCP/TLS connection, login, job receipt, RandomX initialization, worker hashing and actual share acceptance. Record terminal events and confirm the same worker/share counts on the pool dashboard. A connection or rising hashrate alone is insufficient.
6. Pause/resume/stop from both app and notification. Confirm workers stop and no hashing continues after Stop. Change worker count and verify the next session. Exercise screen-off/background and process recreation within Android restrictions.
7. Verify real Android battery/thermal readings, charger-only setting, battery cutoff and naturally observed thermal throttling. Use Android test facilities where available; do not bypass safeguards.
8. Open Settings → Mining Terminal / Live Logs while mining. Verify actual startup, job, connection, submission, acceptance/rejection, reconnect, pause/resume/safeguard and shutdown events. Turn off auto-scroll, inspect older events, filter, clear and confirm new events continue. Compare hashrate logs with the dashboard.
9. Copy/export diagnostics; confirm payout addresses and credentials are redacted, cancellation is harmless and export remains responsive. No shell commands can be executed from this viewer.
10. Test at least one hour of steady operation after basic controls pass. Record accepted/rejected/stale shares, crash/ANR status, thermal/battery behavior and pool evidence. Check for unintended endpoint/wallet rotation. Stop if the device reports unsafe conditions.

Attach evidence for each completed level in `TEST-REPORT.md`. Historical acceptance results must not be copied into the new build's result column.
