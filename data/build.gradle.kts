plugins {
    androidLibrary()
    kotlinAndroid()
    kotlinKapt()
    kotlinAndroidExt()
    ktlint(false)
}

android {
    compileSdkVersion(ProjectConfig.target_sdk)
    buildToolsVersion = ProjectConfig.build_version

    defaultConfig {
        minSdkVersion(ProjectConfig.min_sdk)
        targetSdkVersion(ProjectConfig.target_sdk)
        versionCode = 1
        versionName = "1.0"

        consumerProguardFiles("consumer-rules.pro")

        buildConfigField("String", "PLANDAY_CLIENT_ID", property("PLANDAY_CLIENT_ID").toString())
        buildConfigField("String", "PLANDAY_REFRESH_TOKEN", property("PLANDAY_REFRESH_TOKEN").toString())
    }

    buildTypes {

        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    setOf(
        Deps.Kotlin.stdlib,
        Deps.Splits.prefs,
        Deps.Google.dagger,
        Deps.Utils.timber
    ).forEach(::implementation)
    setOf(
        Deps.Net.retrofit,
        Deps.Net.okhttp,
        Deps.Jetpack.paging,
        Deps.Jetpack.pagingRx,
        Deps.Rx.rxjava,
        Deps.Rx.rxkotlin,
        Deps.Net.retrofitMoshi
    ).forEach(::api)
    setOf(
        Deps.Kapt.dagger
    ).forEach(::kapt)
}
