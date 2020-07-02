// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    dependencyUpdates()
    ktlint()
}

buildscript {
    repositories {
        google()
        jcenter()
        maven("https://plugins.gradle.org/m2/")
    }
    dependencies {
        classpath(Classpath.android)
        classpath(Classpath.kotlin)
        classpath(Classpath.hilt)
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
