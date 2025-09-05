plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")  // Enable kapt plugin for annotation processing
}

android {
    namespace = "com.example.myanimeapp" // change to your app's package name
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.myanimeapp"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Retrofit & Gson converter for API calls
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    // RecyclerView to display list
    implementation(libs.recyclerview)

    // Glide for image loading
    implementation(libs.glide)
    kapt(libs.glide.compiler) // Annotation processor for Glide


// Room dependencies
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler) // Annotation processor for Room

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


}
