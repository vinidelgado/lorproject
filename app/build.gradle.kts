import com.vini.lorproject.buildsrc.Configs
import com.vini.lorproject.buildsrc.Dependencies
import com.vini.lorproject.buildsrc.Versions

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
//    kotlin("kapt")
//    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Configs.compileSdkVersion

    defaultConfig {
        applicationId = Configs.applicationId
        minSdk = Configs.minSdkVersion
        targetSdk = Configs.targetSdkVersion
        versionCode = Configs.versionCode
        versionName = Configs.versionName

        testInstrumentationRunner = Configs.testInstrumentationRunner
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.kotlinCompilerExtensionVersion
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":feature-leaderboards"))
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.composeDefault)
    implementation(Dependencies.composeTooling)
    implementation(Dependencies.composeActivity)
    implementation(Dependencies.composeFundation)
    implementation(Dependencies.composeMaterial)
    implementation(Dependencies.appCompat)
    implementation(Dependencies.material)
    implementation(Dependencies.material3)
//    implementation(Dependencies.daggerHilt)
//    kapt(Dependencies.daggerHiltCompiler)
//    implementation(Dependencies.daggerHiltLifecycle)
//    implementation(Dependencies.hiltNavigationCompose)
//    implementation(Dependencies.retrofit)
//    implementation(Dependencies.retrofitMoshiConverter)
//    implementation(Dependencies.okHttp)
//    implementation(Dependencies.okHttpLoggingInterceptor)
//
//    implementation(Dependencies.roomRuntime)
//    implementation(Dependencies.roomKtx)
//    kapt(Dependencies.roomCompiler)
//
//    implementation(Dependencies.paging3)
//    implementation(Dependencies.windowSize)
//
//    testImplementation(Dependencies.junit4)
//    androidTestImplementation(Dependencies.junitExtensions)
//    androidTestImplementation(Dependencies.espressoCore)
//    androidTestImplementation(Dependencies.composeUiTest)
//    debugImplementation(Dependencies.composeTooling)
//    debugImplementation(Dependencies.testManifest)
}