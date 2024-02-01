@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    kotlin("kapt")
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt.android)
}

android {
    namespace = "ru.radzze.effective_mobile"
    compileSdk = 34

    defaultConfig {
        applicationId = "ru.radzze.effective_mobile"
        minSdk = 27
        targetSdk = 33
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
        kotlinCompilerExtensionVersion = "1.5.2"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

}

dependencies {
    implementation(libs.compose.material)
    implementation(libs.androidx.navigation)

    implementation(libs.datastore.preferences)

    implementation(libs.hilt.android)
//    implementation(libs.hilt.lifecycle.viewmodel)
    implementation(libs.hilt.navigation.compose)
    kapt(libs.hilt.android.compiler)

    implementation(libs.retrofit)

    implementation(libs.room.runtime)
    annotationProcessor(libs.room.compiler)
    kapt(libs.room.compiler)

    implementation(libs.coroutines)

    implementation(libs.lifecycle.viewmodel)

    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.ui)
    implementation(libs.ui.graphics)
    implementation(libs.ui.tooling.preview)
    implementation(libs.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
}

kapt {
    correctErrorTypes = true
}