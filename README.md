# Proton Authenticator - Airgapped Edition

This repository contains a modified version of the Proton Authenticator Android application, specifically adapted for **airgapped (network-isolated) environments**.

## 🔒 Airgapped Modifications

This version has been modified by **multiply.a1** in collaboration with **Rovo Dev AI** to create a fully offline, network-isolated authenticator. The following changes have been implemented:

### 🚫 Network Isolation
- **Internet permission removed** from AndroidManifest.xml
- **Sync functionality disabled** - No cloud synchronization with Proton servers
- **All network-dependent features removed** for complete offline operation

### 🎨 UI Modifications
- **"Discover Proton" section removed** - Eliminated all promotional content for other Proton services (Pass, VPN, Mail, Calendar, Drive)
- **Sync toggle removed** from Security settings
- **Version identifier updated** - Now displays "-airgapped" suffix to clearly indicate this is a network-isolated build

### 🎯 Purpose
This airgapped version is designed for:
- High-security environments requiring network isolation
- Offline-only TOTP/2FA code generation
- Users who prefer complete privacy without any network communication
- Environments where internet access is restricted or prohibited

### ⚠️ Important Notes
- **No online backup/restore functionality** - All data remains local only
- **No account synchronization** - Codes must be manually added to each device
- **No automatic updates** - Manual installation required for updates
- **Local storage only** - All authenticator entries are stored locally on the device

---

## Original Project Information

# Proton Authenticator

This repository contains the source code for the Proton Authenticator Android application.

[<img src="https://play.google.com/intl/en_us/badges/images/generic/en-play-badge.png"
alt="Get it on Google Play"
height="80">](https://play.google.com/store/apps/details?id=proton.android.authenticator)

## How to build

If you want to build the app locally, please refer to the [BUILD.md](./docs/public/BUILD.md) file.

## Develop

If you want to contribute to the application, please refer to the [CONTRIBUTING.md](CONTRIBUTING.md) file.

## Help us translate

If you want to help us to translate the application, you can learn more about it on [our blog post](https://proton.me/blog/translation-community).

## License

The code and data files in this distribution are licensed under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version. See <https://www.gnu.org/licenses/> for a copy of this license.

See [LICENSE](LICENSE) file
