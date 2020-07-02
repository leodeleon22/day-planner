import org.gradle.api.JavaVersion

object ProjectConfig {
    const val appId = "com.leodeleon.dayplanner"
    const val versionName = "1.0"
    const val versionCode = 1
    const val min_sdk = 21
    const val target_sdk = 29
    const val build_version = "29.0.2"
    val java_version = JavaVersion.VERSION_1_8
    const val jvm_target = "1.8"
    const val testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
}