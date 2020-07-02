plugins {
    androidApp()
    kotlinAndroid()
    kotlinKapt()
    kotlinAndroidExt()
    hilt()
    ktlint(false)
}

android {
    compileSdkVersion(ProjectConfig.target_sdk)
    buildToolsVersion = ProjectConfig.build_version
    defaultConfig {
        applicationId = ProjectConfig.appId
        minSdkVersion(ProjectConfig.min_sdk)
        targetSdkVersion(ProjectConfig.target_sdk)
        versionCode = ProjectConfig.versionCode
        versionName = ProjectConfig.versionName
        testInstrumentationRunner = ProjectConfig.testInstrumentationRunner

        buildConfigField("String", "PLANDAY_CLIENT_ID", property("PLANDAY_CLIENT_ID").toString())
        buildConfigField("String", "PLANDAY_REFRESH_TOKEN", property("PLANDAY_REFRESH_TOKEN").toString())
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    buildFeatures {
        dataBinding = true
    }

    compileOptions {
        sourceCompatibility = ProjectConfig.java_version
        targetCompatibility = ProjectConfig.java_version
    }

    kotlinOptions {
        jvmTarget = ProjectConfig.jvm_target
    }
}

kapt {
    useBuildCache = true
}

tasks.withType<Test> {
    useJUnitPlatform()
}

// To see which deps need to update run: ./gradlew dependencyUpdates -Drevision=release
dependencies {
    implementation(project(":data"))
    setOf(
        Deps.Kotlin.stdlib,
        Deps.Kotlin.coroutines,
        Deps.Google.material,
        Deps.Google.hilt,
        Deps.Jetpack.navigationUI,
        Deps.Jetpack.navigationFragment,
        Deps.Jetpack.constraint,
        Deps.Jetpack.appcompat,
        Deps.Jetpack.fragment,
        Deps.Jetpack.activity,
        Deps.Jetpack.cardview,
        Deps.Jetpack.viewModel,
        Deps.Jetpack.viewModelState,
        Deps.Jetpack.liveData,
        Deps.Jetpack.lifecycle,
        Deps.Jetpack.ktx,
        Deps.Jetpack.hiltViewModel,
        Deps.Rx.rxandroid,
        Deps.Net.okhttpLogging,
        Deps.Net.retrofitRxJava,
        Deps.Utils.bindingAdapter,
        Deps.Utils.bindingRecycler,
        Deps.Utils.bindingPaging,
        Deps.Utils.timber,
        Deps.Splits.dialog,
        Deps.Splits.context,
        Deps.Splits.lifecycle,
        Deps.Splits.toast,
        Deps.Splits.services,
        Deps.Splits.resources
    ).forEach(::implementation)

    setOf(
        Deps.Kapt.lifecycle,
        Deps.Kapt.databinding,
        Deps.Kapt.hilt,
        Deps.Kapt.hiltAndroid
    ).forEach(::kapt)

    setOf(
        Deps.Test.kotest,
        Deps.Test.kotestAssert
    ).forEach(::testImplementation)

    setOf(
        Deps.Test.runner,
        Deps.Test.espresso
    ).forEach(::androidTestImplementation)
}
