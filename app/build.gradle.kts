plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.lexilearn"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.lexilearn"
        minSdk = 26
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

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.0")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))
    implementation("androidx.navigation:navigation-runtime-ktx:2.7.7")
    implementation("androidx.navigation:navigation-compose:2.7.7")
    implementation("com.google.accompanist:accompanist-systemuicontroller:0.28.0")
    implementation("com.github.tehras:charts:0.2.4-alpha")
    implementation("com.google.accompanist:accompanist-flowlayout:0.24.9-beta")
    implementation("com.google.accompanist:accompanist-placeholder:0.24.13-rc")
    implementation("com.google.accompanist:accompanist-navigation-material:0.24.13-rc")
//    implementation("com.mohamedrejeb.dnd:compose-dnd:0.2.0")
//    implementation("cn.tinyhai.compose:dragdrop:0.3.1")
//    implementation("com.microsoft.device.dualscreen:draganddrop:1.0.0-alpha01")

//    implementation("androidx.compose.ui:ui-draggable:1.2.0-alpha03")

    implementation("androidx.media3:media3-common:1.3.1")
    implementation("androidx.draganddrop:draganddrop:1.0.0")
    implementation("com.google.code.gson:gson:2.10.1")
//    implementation("com.patrykandpatrick.vico:compose:2.0.0-alpha.20")
//    implementation("com.patrykandpatrick.vico:compose-m2:2.0.0-alpha.20")
//    implementation("com.patrykandpatrick.vico:compose-m3:2.0.0-alpha.20")
//    implementation("com.patrykandpatrick.vico:core:2.0.0-alpha.20")
//    implementation("com.patrykandpatrick.vico:views:2.0.0-alpha.20")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}