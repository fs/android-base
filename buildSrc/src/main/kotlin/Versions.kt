object Versions {
    val TARGET_SDK_VERSION = 28
    val BUILD_TOOLS_VERSION = "28.0.3"
    val MIN_SDK_VERSION = 14
    val KOTLIN = "1.3.11"
    val OKHTTP = "3.3.1"
    val SUPPORT = "28.0.0"
    val RETROFIT = "2.0.2"
    val DETEKT = "1.0.0-RC12"
}

object Libs {
    val SUPPORT_ANNOTATIONS = "com.android.support:support-annotations:${Versions.SUPPORT}"
    val APPCOMPATV7 = "com.android.support:appcompat-v7:${Versions.SUPPORT}"
    val RECYCLER_VIEW = "com.android.support:recyclerview-v7:${Versions.SUPPORT}"
    val RX_JAVA = "io.reactivex:rxjava:1.1.6"
    val RX_ANDROID = "io.reactivex:rxandroid:1.2.1"

    val RETROFIT = "com.squareup.retrofit2:retrofit:${Versions.RETROFIT}"
    val RETROFIT_RX_ADAPTER = "com.squareup.retrofit2:adapter-rxjava:${Versions.RETROFIT}"
    val RETROFIT_GSON_CONVERTER = "com.squareup.retrofit2:converter-gson:${Versions.RETROFIT}"

    val OKHTTP = "com.squareup.okhttp3:okhttp:${Versions.OKHTTP}"
    val OKHTTP_LOGGING_INSPECTOR = "com.squareup.okhttp3:logging-interceptor:${Versions.OKHTTP}"

    val JUNIT = "junit:junit:4.12"
    val ASSERTJ = "com.squareup.assertj:assertj-android:1.0.0"
}
