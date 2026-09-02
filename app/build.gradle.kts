plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.superinterns.islamy"
    compileSdk {
        version = release(37)
    }

    defaultConfig {
        applicationId = "com.superinterns.islamy"
        minSdk = 24
        targetSdk = 37
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            optimization {
                enable = false
            }
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.activity.ktx)
    implementation(libs.appcompat)
    implementation(libs.constraintlayout)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(libs.ext.junit)
    implementation("com.google.android.gms:play-services-location:21.3.0")
    implementation("com.batoulapps.adhan:adhan2:0.0.6")
    implementation(libs.kotlinx.coroutines.android)


}