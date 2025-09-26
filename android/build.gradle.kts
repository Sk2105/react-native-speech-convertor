plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("com.facebook.react") // ✅ needed for react {} block
}

android {
    namespace = "com.reactnativestt"
    compileSdk = 35

    defaultConfig {
        minSdk = 21
    }

    testOptions {
        targetSdk = 35
    }
    sourceSets["main"].java.srcDirs("src/main/java")
}

dependencies {
    implementation("com.facebook.react:react-native:+")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:2.0.0")
}


