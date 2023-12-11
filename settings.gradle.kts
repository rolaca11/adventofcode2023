pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

rootProject.name = "adventofcode2023"
include("day1")
include("day2")
include("day3")
include("day4")
include("day5")
include("day6")
include("day7")
include("day8")
include("day9")
include("day10")
include("day11")
