// Top-level build file where you can add configuration options common to all sub-projects/modules.
apply(plugin = Plugins.versions)

buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(Classpath.android)
        classpath(Classpath.kotlin)
        classpath(Classpath.versions)
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
