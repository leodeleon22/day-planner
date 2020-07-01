plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinAndroidExtensions)
}

android {
    compileSdkVersion (BuildVersions.target_sdk)
    buildToolsVersion = BuildVersions.build_version

    defaultConfig {
        minSdkVersion(BuildVersions.min_sdk)
        targetSdkVersion(BuildVersions.target_sdk)
        versionCode = 1
        versionName = "1.0"

        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Deps.Kotlin.stdlib)
    implementation(Deps.Net.okhttp)
    api(Deps.Net.retrofit)
    implementation(Deps.Net.retrofitMoshi)
    implementation(Deps.Net.retrofitRxJava)
    api(Deps.Jetpack.paging)
    api(Deps.Rx.rxjava)
}
