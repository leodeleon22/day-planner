plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.kotlinAndroidExtensions)
    id(Plugins.hilt)
}

android {
    compileSdkVersion (BuildVersions.target_sdk)
    buildToolsVersion = BuildVersions.build_version
    defaultConfig {
        applicationId = Release.appId
        minSdkVersion(BuildVersions.min_sdk)
        targetSdkVersion(BuildVersions.target_sdk)
        versionCode = 1
        versionName = Release.versionName
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        forEach {
            it.buildConfigField("String", "PLANDAY_CLIENT_ID", properties["PLANDAY_CLIENT_ID"].toString())
            it.buildConfigField("String", "PLANDAY_REFRESH_TOKEN", properties["PLANDAY_REFRESH_TOKEN"].toString())
        }
    }

    dataBinding {
        isEnabled = true
    }

    compileOptions {
        sourceCompatibility = BuildVersions.java
        targetCompatibility = BuildVersions.java
    }
}

kapt {
    useBuildCache = true
}

tasks.withType<Test> {
    useJUnitPlatform()
}

//To see which deps need to update run: ./gradlew dependencyUpdates -Drevision=release
dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(project(":data"))
    implementation(Deps.Kotlin.stdlib)
    implementation(Deps.Kotlin.coroutines)
    implementation(Deps.Google.material)
    implementation(Deps.Google.hilt)
    implementation(Deps.Jetpack.navigationUI)
    implementation(Deps.Jetpack.navigationFragment)
    implementation(Deps.Jetpack.constraint)
    implementation(Deps.Jetpack.appcompat)
    implementation(Deps.Jetpack.fragment)
    implementation(Deps.Jetpack.cardview)
    implementation(Deps.Jetpack.viewModel)
    implementation(Deps.Jetpack.liveData)
    implementation(Deps.Jetpack.ktx)
    implementation(Deps.Rx.rxandroid)
    implementation(Deps.Rx.rxkotlin)
    implementation(Deps.Utils.bindingAdapter)
    implementation(Deps.Utils.bindingRecycler)
    implementation(Deps.Utils.bindingPaging)
    implementation(Deps.Splits.dialog)
    implementation(Deps.Splits.lifecycle)
    implementation(Deps.Splits.prefs)
    implementation(Deps.Splits.snackbar)
    implementation(Deps.Splits.services)
    kapt(Deps.Kapt.lifecycle)
    kapt(Deps.Kapt.databinding)
    kapt(Deps.Kapt.hilt)
    testImplementation(Deps.Test.kotest)
    testImplementation(Deps.Test.kotestAssert)
    androidTestImplementation(Deps.Test.runner)
    androidTestImplementation(Deps.Test.espresso)
}
