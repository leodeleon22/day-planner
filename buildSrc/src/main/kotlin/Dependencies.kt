object Versions {
    const val kotlin = "1.3.72"
    const val ktlint = "9.2.1"
    const val android_plugin = "4.0.0"
    const val lifecycle = "2.3.0-alpha05"
    const val hilt = "2.28.1-alpha"
    const val dagger = "2.28"
    const val moshi = "1.9.3"
}

object Classpath {
    const val android = "com.android.tools.build:gradle:${Versions.android_plugin}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val versions = "com.github.ben-manes:gradle-versions-plugin:0.28.0"
    const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
    const val ktlint = "org.jlleitschuh.gradle:ktlint-gradle:${Versions.ktlint}"
}

object Deps {

    object Jetpack {
        private const val NAVIGATION = "2.3.0"
        private const val PAGING = "2.1.2"
        const val appcompat = "androidx.appcompat:appcompat:1.3.0-alpha01"
        const val constraint = "androidx.constraintlayout:constraintlayout:2.0.0-beta7"
        const val fragment = "androidx.fragment:fragment:1.3.0-alpha06"
        const val activity = "androidx.activity:activity:1.2.0-alpha06"
        const val cardview = "androidx.cardview:cardview:1.0.0"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        const val viewModelState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.lifecycle}"
        const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
        const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
        const val ktx = "androidx.core:core-ktx:1.5.0-alpha01"
        const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:$NAVIGATION"
        const val navigationUI = "androidx.navigation:navigation-ui-ktx:$NAVIGATION"
        const val paging = "androidx.paging:paging-runtime-ktx:$PAGING"
        const val pagingRx = "androidx.paging:paging-rxjava2-ktx:$PAGING"
        const val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha01"
    }

    object Kotlin {
        val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
        val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.5"
    }

    object Rx {
        val rxjava = "io.reactivex.rxjava2:rxjava:2.2.19"
        val rxandroid = "io.reactivex.rxjava2:rxandroid:2.1.1"
        val rxkotlin = "io.reactivex.rxjava2:rxkotlin:2.4.0"
    }

    object Google {
        const val material = "com.google.android.material:material:1.3.0-alpha01"
        const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    }

    object Net {
        private const val RETROFIT = "2.9.0"
        private const val OKHTTP = "4.7.2"
        const val retrofit = "com.squareup.retrofit2:retrofit:$RETROFIT"
        const val retrofitMoshi = "com.squareup.retrofit2:converter-moshi:$RETROFIT"
        const val retrofitRxJava = "com.squareup.retrofit2:adapter-rxjava2:$RETROFIT"
        const val okhttp = "com.squareup.okhttp3:okhttp:$OKHTTP"
        const val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:$OKHTTP"
        const val moshi = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
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
        const val timber = "com.jakewharton.timber:timber:4.7.1"
    }

    object Splits {
        private const val VERSION = "3.0.0-alpha06"
        const val dialog = "com.louiscad.splitties:splitties-alertdialog-appcompat:$VERSION"
        const val lifecycle = "com.louiscad.splitties:splitties-arch-lifecycle:$VERSION"
        const val prefs = "com.louiscad.splitties:splitties-preferences:$VERSION"
        const val toast = "com.louiscad.splitties:splitties-toast:$VERSION"
        const val services = "com.louiscad.splitties:splitties-systemservices:$VERSION"
        const val resources = "com.louiscad.splitties:splitties-resources:$VERSION"
        const val context = "com.louiscad.splitties:splitties-appctx:$VERSION"
    }

    object Kapt {
        const val lifecycle = "android.arch.lifecycle:compiler:${Versions.lifecycle}"
        const val hiltAndroid = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
        const val hilt = "androidx.hilt:hilt-compiler:1.0.0-alpha01"
        const val databinding = "com.android.databinding:compiler:${Versions.android_plugin}"
        const val dagger = "com.google.dagger:dagger-compiler:${Versions.dagger}"
        const val moshi = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"

    }
}