plugins {
    alias(libs.plugins.arch.android.library)
    alias(libs.plugins.arch.di.hilt)
}

android {
    namespace = "com.example.domain.home"
}

dependencies {
    implementation(projects.domain.common)

    implementation(libs.coroutines)
    implementation(libs.kotlin.collections.immutable)
}