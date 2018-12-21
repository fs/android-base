import io.gitlab.arturbosch.detekt.detekt

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
}

apply(from = "checkstyle/checkstyle.gradle")
apply(from = "./jacoco.gradle")

//ext {
//    isCI = "true".equals(System.getenv("CI"))
////    STORE_PASSWORD = System.getenv("PROJECT_NAME_STORE_PASSWORD")
////    KEY_ALIAS = System.getenv("PROJECT_NAME_KEY_ALIAS")
////    KEY_PASSWORD = System.getenv("PROJECT_NAME_KEY_PASSWORD")
//}
//
detekt {
    toolVersion = "1.0.0-RC11"
    input = files("src/main/java")
    filters = ".*/resources/.*,.*/build/.*"
}

android {
    compileSdkVersion(28)
    buildToolsVersion("28.0.3")

    defaultConfig {
        minSdkVersion(14)
        targetSdkVersion(28)

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
//        release {
//            storeFile file("$rootDir/project_name.jks")
//            storePassword STORE_PASSWORD
//            keyAlias KEY_ALIAS
//            keyPassword KEY_PASSWORD
//        }
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
            //            if (isCI) {
//            testCoverageEnabled = true
//            }
        }
        getByName("release") {
            isMinifyEnabled = true
        }
    }

//    testOptions {
//        if (isCI) {
//            unitTests.all {
//                jacoco {
//                    includeNoLocationClasses = true
//                }
//            }
//        }
//    }

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

//    configurations.all {
//        resolutionStrategy.force = "com.android.support:support-annotations:28.0.0"
//    }
}

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.10")
    implementation("com.android.support:appcompat-v7:28.0.0")
    implementation("com.android.support:recyclerview-v7:28.0.0")
    implementation("com.android.support:design:28.0.0")
    implementation("io.reactivex:rxjava:1.1.6")
    implementation("io.reactivex:rxandroid:1.2.1")

    //    implementation retrofitLibs
    //    implementation okHttpLibs

    compileOnly("org.jetbrains:annotations:13.0")
    implementation("com.google.code.gson:gson:2.4")
    implementation("com.github.bumptech.glide:glide:3.7.0")

    implementation("com.jakewharton:butterknife:9.0.0-rc2")
    kapt("com.jakewharton:butterknife-compiler:9.0.0-rc2")

//    testImplementation(unitTestLibs)
//    androidTestImplementation(androidTestsLibs)
}


apply(from = "quality.gradle")
