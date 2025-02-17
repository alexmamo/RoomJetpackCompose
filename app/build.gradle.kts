plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.devtools.ksp)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "ro.alexmamo.roomjetpackcompose"
    compileSdk = 35

    defaultConfig {
        applicationId = "ro.alexmamo.roomjetpackcompose"
        minSdk = 21
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "ro.alexmamo.roomjetpackcompose.HiltTestRunner"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_21.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.get()
    }
}
dependencies {
    //Compose
    implementation(platform(libs.compose.bom))
    implementation(libs.compose.material)
    implementation(libs.compose.material.icons)
    //Navigation
    implementation(libs.navigation.compose)
    //Hilt Navigation Compose
    implementation(libs.hilt.navigation.compose)
    //Hilt
    implementation(libs.hilt)
    ksp(libs.hilt.compiler)
    //Room
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    ksp(libs.room.compiler)
    //Serialization
    implementation(libs.serialization)
    //Tests
    androidTestImplementation(libs.navigation.testing)
    androidTestImplementation(libs.hilt.android.testing)
    androidTestImplementation(libs.runner)
    androidTestImplementation(libs.ui.test.junit4)
    androidTestImplementation(libs.kotlinx.coroutines.test)
    androidTestImplementation(libs.truth)
    debugImplementation(libs.ui.test.manifest)
}