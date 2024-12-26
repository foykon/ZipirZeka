plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "com.example.zipirzeka"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.zipirzeka"
        minSdk = 33
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables.useSupportLibrary = true;
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
    buildFeatures {
        viewBinding {
            enable = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures{
        viewBinding = true
    }
    viewBinding {
        enable = true
    }
}

dependencies {
/*    implementation ("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation ("androidx.appcompat:appcompat-resources:1.6.1")
    implementation ("androidx.appcompat:appcompat:1.6.1")
    implementation ("androidx.activity:activity-ktx:1.8.0")
    implementation ("androidx.activity:activity-compose:1.8.0")
    implementation ("androidx.activity:activity:1.8.0") // 1.9.3 yerine daha eski sürüm
    implementation ("androidx.activity:activity-ktx:1.8.0")

    // Transition bağımlılığı
    implementation ("androidx.transition:transition:1.4.1") // Daha düşük bir sürüm

    // Emoji2 bağımlılıkları
    implementation ("androidx.emoji2:emoji2:1.3.0") // Daha düşük sürüm
    implementation ("androidx.emoji2:emoji2-views-helper:1.3.0")

    // Core bağımlılıkları
    implementation ("androidx.core:core:1.13.0") // Daha düşük bir sürüm
    implementation ("androidx.core:core-ktx:1.13.0")

    // Profileinstaller bağımlılığı
    implementation ("androidx.profileinstaller:profileinstaller:1.3.1") // Daha düşük sürüm

    // Annotation experimental bağımlılığı
    implementation ("androidx.annotation:annotation-experimental:1.3.0")*/



    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation("com.canopas.compose-animated-navigationbar:bottombar:1.0.1")
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}