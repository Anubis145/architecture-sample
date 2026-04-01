package com.example.core.common.buildConfig

data class ArchBuildConfig(
    val debug: Boolean,
    val applicationId: String,
    val versionCode: Int,
    val versionName: String,
    val productFlavor: ProductFlavor,
    val buildType: BuildType,

    val booksApiDevBaseUrl: String,
    val booksApiKey: String
)

enum class BuildType(val value: String) {
    DEBUG("debug"),
    RELEASE("release");

    companion object {
        fun fromString(value: String): BuildType {
            return entries.firstOrNull { it.value == value } ?: error("Invalid build type: $value")
        }
    }
}

enum class ProductFlavor(val value: String) {
    DEV("dev"),
    QA("qa"),
    PROD("prod");

    companion object {
        fun fromString(value: String): ProductFlavor {
            return entries.firstOrNull { it.value == value } ?: error("Invalid product flavor: $value")
        }
    }
}
