import org.gradle.api.JavaVersion

object BuildVersions {
    const val kotlin = "1.3.72"
    const val min_sdk = 21
    const val target_sdk = 29
    const val android_plugin = "4.0.0"
    const val build_version = "29.0.2"
    val java = JavaVersion.VERSION_1_8
}

object Release {
    const val versionName = "1.0"
    const val appId = "com.leodeleon.dayplanner"
}

object Classpath {
    const val android = "com.android.tools.build:gradle:${BuildVersions.android_plugin}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${BuildVersions.kotlin}"
    const val versions = "com.github.ben-manes:gradle-versions-plugin:0.28.0"
    const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${Deps.Versions.HILT}"
}

object Plugins {
    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinKapt = "kotlin-kapt"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
    const val versions = "com.github.ben-manes.versions"
    const val hilt = "dagger.hilt.android.plugin"
}

object Deps {
    internal object Versions {
        const val LIFECYCLE = "2.3.0-alpha05"
        const val HILT = "2.28.1-alpha"
    }

    object Jetpack {
        private const val NAVIGATION = "2.3.0"
        const val appcompat = "androidx.appcompat:appcompat:1.3.0-alpha01"
        const val constraint = "androidx.constraintlayout:constraintlayout:2.0.0-beta7"
        const val fragment = "androidx.fragment:fragment:1.3.0-alpha06"
        const val cardview = "androidx.cardview:cardview:1.0.0"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.LIFECYCLE}"
        const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.LIFECYCLE}"
        const val ktx = "androidx.core:core-ktx:1.5.0-alpha01"
        const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:$NAVIGATION"
        const val navigationUI = "androidx.navigation:navigation-ui-ktx:$NAVIGATION"
        const val paging = "androidx.paging:paging-runtime-ktx:3.0.0-alpha02"
    }

    object Kotlin {
        val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${BuildVersions.kotlin}"
        val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.5"
    }

    object Rx {
        val rxjava = "io.reactivex.rxjava3:rxjava:3.0.4"
        val rxandroid = "io.reactivex.rxjava3:rxandroid:3.0.0"
        val rxkotlin = "io.reactivex.rxjava3:rxkotlin:3.0.0"
    }

    object Google {
        const val material = "com.google.android.material:material:1.3.0-alpha01"
        const val hilt = "com.google.dagger:hilt-android:${Versions.HILT}"
    }

    object Net {
        private const val RETROFIT = "2.9.0"
        const val retrofit = "com.squareup.retrofit2:retrofit:$RETROFIT"
        const val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:$RETROFIT"
        const val retrofitRxJava = "com.squareup.retrofit2:adapter-rxjava3:$RETROFIT"
        const val okhttp = "com.squareup.okhttp3:okhttp:4.7.2"
    }

    object Test {
        private const val KOTEST = "4.1.1"
        const val kotest = "io.kotest:kotest-runner-junit5-jvm:$KOTEST"
        const val kotestAssert = "io.kotest:kotest-assertions-core-jvm:$KOTEST"
        const val espresso = "com.android.support.test.espresso:espresso-core:3.0.2"
        const val runner = "com.android.support.test:runner:1.0.2"
    }

    object Utils {
        private const val ADAPTER = "4.0.0"
        const val bindingAdapter = "me.tatarka.bindingcollectionadapter2:bindingcollectionadapter:$ADAPTER"
        const val bindingRecycler = "me.tatarka.bindingcollectionadapter2:bindingcollectionadapter-recyclerview:$ADAPTER"
        const val bindingPaging = "me.tatarka.bindingcollectionadapter2:bindingcollectionadapter-paging:$ADAPTER"
    }

    object Splits {
        private const val VERSION = "3.0.0-alpha06"
        const val dialog = "com.louiscad.splitties:splitties-alertdialog-appcompat:$VERSION"
        const val lifecycle = "com.louiscad.splitties:splitties-arch-lifecycle:$VERSION"
        const val prefs = "com.louiscad.splitties:splitties-preferences:$VERSION"
        const val snackbar = "com.louiscad.splitties:splitties-snackbar:$VERSION"
        const val services = "com.louiscad.splitties:splitties-systemservices:$VERSION"
    }

    object Kapt {
        const val lifecycle = "android.arch.lifecycle:compiler:${Versions.LIFECYCLE}"
        const val hilt = "com.google.dagger:hilt-android-compiler:${Versions.HILT}"
        const val databinding = "com.android.databinding:compiler:${BuildVersions.android_plugin}"
    }
}