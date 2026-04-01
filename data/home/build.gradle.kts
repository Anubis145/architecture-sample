plugins {
    alias(libs.plugins.arch.android.library)
    alias(libs.plugins.arch.di.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.example.data.home"
}

dependencies {
    implementation(projects.core.network)
    implementation(projects.domain.home)
    implementation(projects.domain.common)
    implementation(projects.data.common)

    implementation(libs.coroutines)
    implementation(libs.kotlin.serialization.json)
}