// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {

    }

}

plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
    id("org.jetbrains.kotlin.kapt") version "1.8.0" apply false
    alias(libs.plugins.hilt) apply false
    id("com.google.devtools.ksp") version "1.9.0-1.0.13" apply false

}

