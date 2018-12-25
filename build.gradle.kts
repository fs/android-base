buildscript {
    apply(from = "./deps.gradle.kts")
    repositories {
        google()
        maven(url = "https://plugins.gradle.org/m2/")
        jcenter()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:3.4.0-alpha09")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.KOTLIN}")
    }
}

allprojects {
    repositories {
        jcenter()
        google()
    }
}
