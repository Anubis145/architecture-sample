plugins {
    alias(libs.plugins.arch.android.library)
    alias(libs.plugins.arch.ui.compose)
}

android {
    namespace = "com.example.core.common"
}

dependencies {
    implementation(libs.kotlin.datetime)
    implementation(libs.kotlin.serialization.json)
}