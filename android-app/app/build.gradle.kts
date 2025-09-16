import org.gradle.internal.os.OperatingSystem
import java.io.File

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.curtesmalteser.hellorust"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.curtesmalteser.hellorust"
        minSdk = 28
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        ndkVersion = "28.2.13676358"

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
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = "21"
    }
    buildFeatures {
        compose = true
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
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}

// cargo install cargo-ndk

val ndkApi = android.defaultConfig.minSdk
val rustLibPath = "$projectDir/../../hello_rust_lib"

tasks.register<Exec>("buildRust") {
    group = "rust"
    val cargoPath = "${System.getProperty("user.home")}/.cargo/bin/cargo"
    workingDir(rustLibPath)
    commandLine(
        cargoPath, "ndk",
        "-t", "arm64-v8a",
        "-t", "armeabi-v7a",
        "-t", "x86",
        "-t", "x86_64",
        "-P", "$ndkApi",
        "-o", "$projectDir/src/main/jniLibs",
        "build", "--release"
    ).apply {
        println("run if fails: cd $rustLibPath && ${commandLine.joinToString(separator = " ")}\n")
    }
}

tasks.named("preBuild") {
    dependsOn("buildRust")
}

tasks.named("preBuild") {
    dependsOn("clean")
}

