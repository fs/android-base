buildscript {
    apply(from = "./deps.gradle.kts")
    repositories {
        google()
        maven(url = "https://plugins.gradle.org/m2/")
        jcenter()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:3.4.0-alpha09")
        classpath("com.google.guava:guava:17.0")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.3.10")
    }
}

allprojects {
    repositories {
        jcenter()
        google()
    }
}
