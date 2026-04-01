package com.buildConfig

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension
import com.secrets.booksApi.getBooksApiKey
import com.secrets.getLocalKey
import java.io.File

fun CommonExtension<*, *, *, *, *, *>.configureProductFlavors() {
    flavorDimensions += "env"

    productFlavors {
        create(AppEnvironment.DEV.value)
        create(AppEnvironment.PROD.value)

        all {
            dimension = "env"

            val booksApiDevBaseUrl = "https://api.bigbookapi.com"
            buildConfigField("String", "BOOKS_API_BASE_URL_DEV", "\"$booksApiDevBaseUrl\"")
        }
    }
}

fun ApplicationExtension.configureBuildType(rootProjectDir: File) {
    buildTypes {
        getByName(AppEnvironment.DEBUG.value) {
            signingConfig = signingConfigs.getByName(AppEnvironment.DEBUG.value)
        }

        //getByName(AppEnvironment.RELEASE.value) {
        //    signingConfig = signingConfigs.getByName(AppEnvironment.RELEASE.value)
        //    isMinifyEnabled = true
        //    isShrinkResources = true
        //}

        all {
            val booksApiKey = getBooksApiKey(rootProjectDir)
            buildConfigField("String", "BOOKS_API_KEY", "\"$booksApiKey\"")
        }
    }
}

fun ApplicationExtension.configureAppSuffix() {
    productFlavors {
        named(AppEnvironment.DEV.value) {
            versionNameSuffix = "-debug"
            applicationIdSuffix = ".debug"
        }
        named(AppEnvironment.PROD.value) {  }
    }
}
