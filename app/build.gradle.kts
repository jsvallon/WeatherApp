plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("kotlin-parcelize")
}

android {
    namespace = "com.llcvmlr.weatherappchalleneg"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.llcvmlr.weatherappchalleneg"
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
    implementation(libs.androidx.lifecycle.runtime.compose.android)
    implementation(libs.androidx.runtime.livedata)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    implementation ("androidx.compose.material3:material3:1.2.0") // or latest version



    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.0")


    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")

    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
    implementation ("org.jetbrains.kotlin:kotlin-stdlib:1.6.10")
    implementation ("androidx.core:core-ktx:1.7.0")
    implementation ("androidx.appcompat:appcompat:1.4.1")
    implementation ("com.google.android.material:material:1.5.0")
    implementation ("androidx.constraintlayout:constraintlayout:2.1.2")
    implementation ("androidx.lifecycle:lifecycle-runtime-ktx:2.4.0")
    implementation ("androidx.activity:activity-compose:1.4.0")
    implementation ("androidx.hilt:hilt-navigation-compose:1.1.0-alpha01")

    implementation ("androidx.compose.material3:material3-window-size-class:1.1.0-alpha07") // or the latest version

    implementation ("androidx.lifecycle:lifecycle-viewmodel-compose:2.4.0")
    //Hilt Version
    implementation("com.google.dagger:hilt-android:2.46.1")
    kapt("com.google.dagger:hilt-android-compiler:2.46.1")
    kapt ("com.google.dagger:hilt-compiler:2.46.1")


    implementation ("com.google.android.gms:play-services-location:21.0.1")

    implementation ("androidx.navigation:navigation-compose:2.4.0-rc01")

    // For previewing composables
    debugImplementation ("androidx.compose.ui:ui-tooling:1.1.0")
    debugImplementation ("androidx.compose.ui:ui-test-manifest:1.1.0")

    // Kotlin Coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")

    implementation ("io.coil-kt:coil-compose:2.2.2")  // Ensure this is the latest version

    //Retrofit
    implementation ("com.squareup.retrofit2:retrofit:2.3.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.3.0")

    // Retrofit Coroutines Support - Deprecated - No more required
    implementation ("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")

    // Retrofit with Moshi Converter
    implementation ("com.squareup.retrofit2:converter-moshi:2.9.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.12.0")

    implementation ("com.squareup.retrofit2:converter-scalars:2.9.0")

    implementation("com.google.accompanist:accompanist-systemuicontroller:0.24.7-alpha")

    implementation ("io.github.vanpra.compose-material-dialogs:datetime:0.8.1-rc")
    coreLibraryDesugaring ("com.android.tools:desugar_jdk_libs:1.1.6")

    implementation("androidx.core:core-splashscreen:1.0.1")

    testImplementation ("junit:junit:4.13.2")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.2")
    androidTestImplementation ("androidx.test.ext:junit:1.1.3")
    androidTestImplementation ("androidx.test.espresso:espresso-core:3.4.0")
    androidTestImplementation ("com.squareup.okhttp3:mockwebserver:4.9.2")

    // For LiveData and ViewModel testing
    testImplementation ("androidx.arch.core:core-testing:2.2.0")
    androidTestImplementation ("android.arch.core:core-testing:1.1.0")
}