plugins {
    alias(libs.plugins.arch.android.library)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.example.domain.common"
}

dependencies {
    implementation(libs.kotlin.collections.immutable)
    implementation(libs.kotlin.serialization.json)
    implementation(libs.coroutines)
}