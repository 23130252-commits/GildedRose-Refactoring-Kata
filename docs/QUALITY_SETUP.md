# Code Quality Setup Guide

This guide explains how to set up **Qlty.sh** (Code Climate Quality) for the GildedRose-Refactoring-Kata project to automatically analyze Java code quality on every push and pull request.

## Overview

[Qlty.sh](https://qlty.sh) is a code quality platform (formerly Code Climate Quality) that provides:
- **Code complexity analysis** – flags methods that are too complex
- **Code duplication detection** – identifies copy-pasted code
- **Security vulnerability scanning** – highlights common security issues
- **Code style violations** – enforces coding standards
- **Performance issue detection** – surfaces inefficient patterns

## Files Added

| File | Purpose |
|------|---------|
| `.qlty.yml` | Qlty.sh project configuration |
| `.codeclimate.yml` | Code Climate integration (optional fallback) |
| `.github/workflows/qlty-quality-check.yml` | GitHub Actions CI workflow |
| `docs/QUALITY_SETUP.md` | This documentation file |

---

## 1. Create a Qlty.sh Account

1. Visit [https://qlty.sh](https://qlty.sh) and click **Sign up**.
2. Sign in with your GitHub account to allow repository access.
3. Once logged in, navigate to your **dashboard**.

---

## 2. Add Your Repository to Qlty.sh

1. From the Qlty dashboard, click **Add Repository**.
2. Select the `GildedRose-Refactoring-Kata` repository.
3. Qlty will run an initial analysis and display the results.

---

## 3. Obtain a Qlty API Key

1. In the Qlty dashboard, go to **Settings → API Keys** (or your profile settings).
2. Click **Generate API Key**.
3. Copy the key — you will need it in the next step.

---

## 4. Configure the API Key in GitHub Secrets

1. Open the repository on GitHub.
2. Go to **Settings → Secrets and variables → Actions**.
3. Click **New repository secret**.
4. Set:
   - **Name**: `QLTY_API_KEY`
   - **Value**: *(paste the API key you copied)*
5. Click **Add secret**.

The GitHub Actions workflow (`.github/workflows/qlty-quality-check.yml`) reads this secret automatically via `${{ secrets.QLTY_API_KEY }}`.

---

## 5. Trigger the Workflow

After adding the secret, push a commit or open a pull request targeting `main` or `develop`. The workflow will:

1. Check out the code.
2. Build the Java project with Maven.
3. Install the Qlty CLI.
4. Run `qlty analyze` and report findings.
5. Upload the quality report as a workflow artifact.

---

## 6. View Quality Reports

### GitHub Actions artifacts
Each workflow run uploads a report under the **Artifacts** section of the run summary.

### Qlty Dashboard
Visit `https://qlty.sh/<your-org>/<repo>` to see:
- Overall maintainability grade
- Issue breakdown by category (complexity, duplication, style, …)
- Trend graphs across commits
- Per-file quality scores

---

## Quality Gates and Metrics

### Complexity Thresholds (`.codeclimate.yml`)

| Check | Threshold |
|-------|-----------|
| Method complexity (cyclomatic) | 5 |
| Method lines | 25 |
| Method count per class | 20 |
| File lines | 250 |
| Argument count | 4 |
| Nested control flow depth | 4 |
| Return statements per method | 4 |

### Java-Specific Analyzers (`.qlty.yml`)

| Tool | Purpose |
|------|---------|
| **Checkstyle** | Code style and formatting rules |
| **PMD** | Potential bugs, dead code, suboptimal code |
| **SpotBugs** | Common bug patterns and security issues |

---

## Troubleshooting

### Workflow fails with "command not found: qlty"
The `curl` install step adds the binary to `$HOME/.qlty/bin`. Make sure the workflow step that calls `qlty` includes:
```bash
export PATH="$HOME/.qlty/bin:$PATH"
```

### No API key set
If `QLTY_API_KEY` secret is missing, the `qlty analyze` command still runs locally but will not upload results to the Qlty dashboard. Add the secret as described in step 4 above.

### Maven build fails
Ensure the Java version in the workflow (`java-version: '11'`) is compatible with the project's `pom.xml` (`<java.version>1.8</java.version>`). JDK 11 can compile Java 8 source code without changes.
