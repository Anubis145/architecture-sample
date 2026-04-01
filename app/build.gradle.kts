
plugins {
    alias(libs.plugins.arch.android.application)
    alias(libs.plugins.arch.ui.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.arch.di.hilt)
    alias(libs.plugins.arch.navigation.compose.destinations)
}

android {
    namespace = "com.example.architecturetest"

    defaultConfig {
        applicationId = "com.example.architecturetest"
    }

    buildFeatures {
        buildConfig = true
    }

    packaging {
        resources {
            excludes += "META-INF/LICENSE.md"
            excludes += "META-INF/LICENSE-notice.md"
        }
    }
}

dependencies {
    implementation(projects.core.ui)
    implementation(projects.core.common)
    implementation(projects.core.network)

    implementation(projects.data.common)
    implementation(projects.data.home)

    implementation(projects.domain.common)
    implementation(projects.domain.home)

    implementation(projects.features.home)

    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.paging.compose)
    implementation(libs.kotlin.collections.immutable)
    implementation(libs.kotlin.datetime)
    implementation(libs.kotlin.serialization.json)
}