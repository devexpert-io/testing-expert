@file:Suppress("UnstableApiUsage")

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.devexperto.testingexpert.appTestShared"
    compileSdk = 35

    defaultConfig {
        minSdk = 24
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
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
}

dependencies {
    implementation(project(":app"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.9.0")
    implementation("junit:junit:4.13.2")
    implementation(platform("androidx.compose:compose-bom:2024.11.00"))
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-test-junit4")
    implementation("androidx.test.ext:junit-ktx:1.2.1")
    implementation("androidx.test.espresso:espresso-contrib:3.6.1")
    implementation("androidx.test.espresso:espresso-idling-resource:3.6.1")
    implementation("androidx.test:rules:1.6.1")
    implementation("com.google.dagger:hilt-android-testing:2.52")
    implementation("com.squareup.okhttp3:mockwebserver:4.12.0")
    implementation("app.cash.turbine:turbine:1.2.0")
    implementation("io.mockk:mockk-android:1.13.13")
    implementation("androidx.room:room-runtime:2.6.1")
}