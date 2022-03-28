# Dawn API [![Latest release](https://img.shields.io/github/release/DawnTeamMC/DawnAPI.svg)](https://github.com/DawnTeamMC/DawnAPI/releases/latest) [![License](https://img.shields.io/github/license/DawnTeamMC/DawnAPI.svg)](https://github.com/DawnTeamMC/DawnAPI/blob/master/LICENSE) [![](http://cf.way2muchnoise.eu/full_dawn_downloads.svg)](https://www.curseforge.com/minecraft/mc-mods/dawn)

[![Dawn API](https://raw.githubusercontent.com/DawnTeamMC/DawnTeamMC/master/dawn_api/header.png)](https://github.com/DawnTeamMC/DawnAPI/wiki)

## Using Dawn API in your project

In order to use Dawn API as a library for your project, add the following in your ``build.gradle``:
```gradle
repositories {
    maven { url 'https://jitpack.io' }
    
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
dawn_version = 3.3.0
```
