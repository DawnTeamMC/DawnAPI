[![Dawn API](https://dawnteammc.github.io/dawn_api/images/header.png)](https://dawnteammc.github.io/)

# 🌙 Dawn API
[![Latest release](https://img.shields.io/github/release/DawnTeamMC/DawnAPI.svg)](https://github.com/DawnTeamMC/DawnAPI/releases/latest)
[![CurseForge downloads](http://cf.way2muchnoise.eu/full_399309_downloads.svg)](https://www.curseforge.com/minecraft/mc-mods/dawn)
[![License (LGPL v3.0)](https://img.shields.io/badge/code%20license-LGPL%20v3.0-green.svg?style=flat-square)](https://www.gnu.org/licenses/lgpl-3.0.en.html)
[![GitHub stars](https://img.shields.io/github/stars/DawnTeamMC/DawnAPI.svg?style=flat-square)]()

[![Discord user count](https://img.shields.io/discord/504608980799062036.svg?logoColor=FFFFFF&logo=discord&color=7289DA&style=flat-square)](https://discord.gg/8ksTVJu)
[![Twitter followers](https://img.shields.io/twitter/follow/DawnTeamMC.svg?logo=twitter&label=twitter&style=flat-square)](https://twitter.com/DawnTeamMC)

The Dawn API is a library mod for the latest version of Minecraft that adds utility classes for constructing a typical mod for the Dawn Team.  
It is designed to be handy and ease with the creation of any feature in the game through builder classes, and a variety of tools for data fixing.

## Using Dawn API in your project

In order to use Dawn API as a library for your project, add the following in your ``build.gradle``:
```gradle
repositories {
    maven { url 'https://jitpack.io' }                // Required for Dawn API
    
    maven { url 'https://maven.shedaniel.me/' }       // Required for Cloth Config
    maven { url 'https://maven.terraformersmc.com/' } // Required for Mod Menu
    maven { url 'https://maven.ryanliptak.com/' }     // Required for AppleSkin
}

dependencies {
    modApi "com.github.DawnTeamMC:DawnAPI:v${dawn_version}"
}
```

Then, add the version in your ``gradle.properties``:
```properties
dawn_version = [VERSION]
```
Where [VERSION] is the version of Dawn API you want to use.

## 👾 Features
Other than being a useful API, the Dawn API is also bundled with some in-game tools too:

- `/health` - A command that allows easy control over an entity's health.
- `/foodbar` - A command that allows easy control over an entity's food/saturation points.
- `/motion` - A command that allows easy control over an entity's motion (velocity).
- `/export` - A command that can export information/files of the game's content.
- A custom TNT entity with more NBT data parameters than the vanilla TNT entity.
- A flying block entity which reflects the flying counterpart of the vanilla falling block entity.

## 📦 Download
We use [CurseForge](https://www.curseforge.com/minecraft/mc-mods/dawn) and [Modrinth](https://modrinth.com/mod/dawn) to publish **stable builds** of the Dawn API for Minecraft.

You can download the latest stable builds from both pages without signing up for an account, although [downloading on CurseForge](https://www.curseforge.com/minecraft/mc-mods/dawn) is currently preferred.

### Required mod
⚠ The Dawn API **needs** the Fabric API to be installed: [GitHub](https://github.com/FabricMC/fabric) / [CurseForge](https://www.curseforge.com/minecraft/mc-mods/fabric-api) / [Modrinth](https://modrinth.com/mod/fabric-api)

### Compatible mods
The Dawn API comes with built-in support for the following mods:

- Cloth Config: [GitHub](https://github.com/shedaniel/cloth-config) / [CurseForge](https://www.curseforge.com/minecraft/mc-mods/cloth-config)
- Mod Menu: [GitHub](https://github.com/TerraformersMC/ModMenu) / [CurseForge](https://www.curseforge.com/minecraft/mc-mods/modmenu)
- AppleSkin: [GitHub](https://github.com/squeek502/AppleSkin) / [CurseForge](https://www.curseforge.com/minecraft/mc-mods/appleskin) / [Modrinth](https://modrinth.com/mod/appleskin)

## 🐛 Reporting bugs
If you're running into bugs or other problems, feel free to open an issue on our [issue tracker](https://github.com/DawnTeamMC/DawnAPI/issues).

## 🔧 Contributing
Please refer to the [contributing guide](https://github.com/DawnTeamMC/DawnAPI/blob/master/CONTRIBUTING.md) for more information.

### Translations
The Dawn Team mods makes use of crowdsourced translations using Crowdin.  
You can help translate the Dawn Team mods to any language supported in Minecraft by joining our team of avid translators [here](https://crowdin.com/project/dawnteam).

## ❤️ Support
[![Patreon supporters](https://img.shields.io/endpoint.svg?url=https%3A%2F%2Fshieldsio-patreon.vercel.app%2Fapi%3Fusername%3DHugman%26type%3Dpatrons&style=flat-square)](https://patreon.com/Hugman)

You can support the Dawn API on the [Patreon page of the founder, main developer and maintainer of the Dawn Team mods (Hugman)](https://patreon.com/Hugman).

By supporting Hugman, you can get access to the following:

- Vote for the next features to be added to the Dawn Team mods
- Get exclusive screenshots of the next features to be added to Dawn Team mods
- Get early access to the latest beta versions of Dawn Team mods with new features
- Get early access to new mods from the Dawn Team mods

**We do not want to lock any in-game feature of the Dawn Team mods behind a paywall, because we believe that any Minecraft mod should forever remain free to download and fully exploit/use.**  
Supporting via Patreon is a more of way to help Hugman to continue to improve the mods and show the gratitude you might have towards Hugman's work.
Some money you donate may be used to pay for new features, such as music or art, but not all of it.
