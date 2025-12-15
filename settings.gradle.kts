rootProject.name = "OfflineEncryptor"

pluginManagement {
    repositories {
        gradlePluginPortal()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
    plugins {
        id("java")
        id("com.gradleup.shadow") version "9.3.0" apply false
        id("io.papermc.paperweight.userdev") version "2.0.0-beta.19"
    }
}

include(":common", ":paper", ":velocity")