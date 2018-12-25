import io.gitlab.arturbosch.detekt.extensions.DetektExtension

plugins {
    id("com.android.application")
    id("io.gitlab.arturbosch.detekt").version(Versions.DETEKT)
    kotlin("android")
    kotlin("android.extensions")
}

apply(from = "checkstyle/checkstyle.gradle")
apply(from = "./jacoco.gradle")

//   val STORE_PASSWORD = System.getenv("PROJECT_NAME_STORE_PASSWORD")
//   val KEY_ALIAS = System.getenv("PROJECT_NAME_KEY_ALIAS")
//   val KEY_PASSWORD = System.getenv("PROJECT_NAME_KEY_PASSWORD")

detekt {
    toolVersion = Versions.DETEKT
    input = files("src/main/java")
    filters = ".*/resources/.*,.*/build/.*"
}

android {
    compileSdkVersion(Versions.TARGET_SDK_VERSION)
    buildToolsVersion(Versions.BUILD_TOOLS_VERSION)

    defaultConfig {
        minSdkVersion(Versions.MIN_SDK_VERSION)
        targetSdkVersion(Versions.TARGET_SDK_VERSION)

        applicationId = "com.flatstack.android"
        versionCode = 1
        versionName = "0.9.1"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
        javaCompileOptions.annotationProcessorOptions.includeCompileClasspath = true
    }

    flavorDimensions("environment")

    signingConfigs {
        getByName("debug") {
            storeFile = file("$rootDir/debug.jks")
            storePassword = "12345678"
            keyAlias = "debug"
            keyPassword = "12345678"
        }
        create("release") {
//            storeFile file("$rootDir/project_name.jks")
//            storePassword STORE_PASSWORD
//            keyAlias KEY_ALIAS
//            keyPassword KEY_PASSWORD
        }
    }

    productFlavors {
        create("staging") {
            buildConfigField("String", "API_URL", "\"https://example-staging.com\"")
            applicationIdSuffix = ".staging"
            setDimension("environment")
        }

        create("production") {
            buildConfigField("String", "API_URL", "\"https://example.com\"")
            setDimension("environment")
        }
    }

    buildTypes {
        getByName("debug") {
        }
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            isUseProguard = true
//            signingConfig = signingConfigs.getByName("release")
            proguardFiles(getDefaultProguardFile ("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        setTargetCompatibility(JavaVersion.VERSION_1_8)
    }

    dexOptions {
        preDexLibraries = true
    }

    packagingOptions {
        exclude("META-INF/LICENSE")
        exclude("META-INF/NOTICE")
        exclude("META-INF/services/javax.annotation.processing.Processor")
    }

    lintOptions {
        textReport = true
        textOutput("stdout")
        setLintConfig(file("$projectDir/lint.xml"))
        setWarningsAsErrors(true)
    }

    configurations.all {
        resolutionStrategy.force(Libs.SUPPORT_ANNOTATIONS)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.KOTLIN}")
    implementation(Libs.APPCOMPATV7)
    implementation(Libs.RECYCLER_VIEW)
    implementation(Libs.RX_JAVA)
    implementation(Libs.RX_ANDROID)

    implementation("com.google.code.gson:gson:2.4")
    implementation("com.github.bumptech.glide:glide:3.7.0")

    testImplementation(Libs.JUNIT)
    testImplementation(Libs.ASSERTJ)
    testImplementation(Libs.SUPPORT_ANNOTATIONS)
}


apply(from = "quality.gradle")
