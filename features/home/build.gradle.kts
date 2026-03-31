plugins {
    alias(libs.plugins.arch.android.library)
    alias(libs.plugins.arch.ui.compose)
    alias(libs.plugins.arch.di.hilt)
    alias(libs.plugins.arch.navigation.compose.destinations)
}

android {
    namespace = "com.example.features.home"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.ui)
    implementation(projects.domain.home)
    
    implementation(libs.kotlin.datetime)
    implementation(libs.androidx.appcompat)
}