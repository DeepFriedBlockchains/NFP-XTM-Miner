#!/usr/bin/env python3
"""Ensure repository source downloads retain every native provenance marker."""
from pathlib import Path
import io
import json
import subprocess
import tarfile

root = Path(__file__).resolve().parents[2]
lock = json.loads((root / 'DEPENDENCY_LOCK.json').read_text())
paths = [f"third_party/src/{item['name']}/.cryptosteer-upstream-commit" for item in lock['native']]
data = subprocess.check_output(['git', 'archive', '--format=tar', 'HEAD', '--', *paths], cwd=root)
with tarfile.open(fileobj=io.BytesIO(data)) as archive:
    for item, path in zip(lock['native'], paths):
        marker = archive.extractfile(path)
        assert marker is not None, path
        assert marker.read().decode().strip() == item['commit'], path
print('Git source-archive native provenance markers: PASS')
