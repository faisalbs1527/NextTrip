plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id ("kotlin-parcelize")
}

android {
    namespace = "com.example.nexttrip"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.nexttrip"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.appcompat)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation (libs.material3)
    //icon
    implementation (libs.androidx.material.icons.extended)
    //navigation compose
    implementation(libs.androidx.navigation.compose)

    implementation (libs.retrofit)

    implementation (libs.converter.gson)

    implementation (libs.glide)

    annotationProcessor (libs.compiler)

    implementation(libs.okhttp)
    implementation(libs.logging.interceptor)

    // Hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.android.compiler)
    //for hiltviewmodel
    implementation(libs.androidx.hilt.navigation.compose)

    implementation (libs.coil.compose)

    // Parcelable library (if needed)
    implementation(libs.androidx.core.ktx.v1100)

    //barcode
    implementation (libs.core)

    //Room
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:2.6.1")
    // Kotlin Extensions and Coroutines support for Room
    implementation(libs.androidx.room.ktx)

}