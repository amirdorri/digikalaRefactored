import java.util.Properties

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("com.google.dagger.hilt.android")
    kotlin("plugin.serialization") version "2.1.20"
 //   id("org.jetbrains.kotlin.plugin.serialization")
  //  id("androidx.compose.compiler")
    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0"
}

val apikeyPropertiesFile = rootProject.file("key.properties")
val apikeyProperties = Properties()

if (apikeyPropertiesFile.exists()) {
    apikeyPropertiesFile.inputStream().use { apikeyProperties.load(it) }
} else {
    println("⚠️ key.properties file not found!")
}

android {
    namespace = "com.example.digikala"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.digikala"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        // Extract values from key.properties safely
        val xApiKey = apikeyProperties.getProperty("X_API_KEY", "")
        val key = apikeyProperties.getProperty("KEY", "")
        val iv = apikeyProperties.getProperty("IV", "")
        // Properly format and add buildConfigField values
        buildConfigField("String", "X_API_KEY", "\"$xApiKey\"")
        buildConfigField("String", "KEY", "\"$key\"")
        buildConfigField("String", "IV", "\"$iv\"")

        ksp {
            arg("room.schemaLocation", "$projectDir/schemas")
        }

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "2.0.0"//1.5.1
    }
    packaging {
        resources.excludes.add("/META-INF/{AL2.0,LGPL2.1}")
    }
}



dependencies {
    // Core Compose dependencies
    implementation("androidx.core:core-ktx:1.15.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.7")
    implementation("androidx.activity:activity-compose:1.10.1") //1.9.3
    implementation(platform("androidx.compose:compose-bom:2025.03.01"))//compose-bom:2024.11.00
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material:material:1.7.8") //1.7.5
    implementation("androidx.compose.material:material-icons-core:1.7.8")//
    implementation("androidx.compose.material:material-icons-extended:1.7.8")//
    implementation("androidx.compose.compiler:compiler:1.5.15") // compose compiler

    // Testing dependencies
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2025.03.01"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")

    // Retrofit dependencies
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    // Room dependencies
    implementation("androidx.room:room-runtime:2.6.1")
    ksp("androidx.room:room-compiler:2.6.1")
    implementation("androidx.room:room-ktx:2.6.1")

    // DataStore
    implementation("androidx.datastore:datastore-preferences:1.1.4")

    // Hilt dependencies
    implementation("com.google.dagger:hilt-android:2.51.1")
    ksp("com.google.dagger:hilt-compiler:2.51.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")

    // Compose Navigation
    implementation("androidx.navigation:navigation-compose:2.8.9")

    // Lottie for animations
    implementation("com.airbnb.android:lottie-compose:6.4.0")

    // Coil for image loading
    implementation("io.coil-kt:coil-compose:2.6.0")

    // Swipe Refresh support
    implementation("com.google.accompanist:accompanist-swiperefresh:0.34.0")

    // System UI Controller support
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.34.0")

    // Pager support
    implementation("com.google.accompanist:accompanist-pager:0.34.0")
    implementation("com.google.accompanist:accompanist-pager-indicators:0.34.0")

    //kotlinx serialization
    //implementation ("org.jetbrains.kotlinx:kotlinx-serialization-json:1.7.3")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.8.0")

    // zarin pal
    implementation("com.zarinpal:payment-provider-ktx:0.5.3")

    //paging
    implementation ("androidx.paging:paging-compose:3.3.6")

    //chart  2.1.2
    implementation ("com.patrykandpatrick.vico:compose:1.13.0")

    //gson
    implementation ("com.google.code.gson:gson:2.11.0")

}


ksp {
    arg("room.schemaLocation", "$projectDir/schemas")
}


