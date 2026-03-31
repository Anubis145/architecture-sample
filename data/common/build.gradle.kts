plugins {
    alias(libs.plugins.arch.android.library)
    alias(libs.plugins.arch.di.hilt)
}

android {
    namespace = "com.example.data.common"
}

dependencies {
    implementation(projects.domain.common)
    implementation(projects.core.network)
}