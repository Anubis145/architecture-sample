package com.buildConfig

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.CommonExtension

fun CommonExtension<*, *, *, *, *, *>.configureProductFlavors() {
    flavorDimensions += "env"

    productFlavors {
        create(AppEnvironment.DEV.value)
        create(AppEnvironment.QA.value)
        create(AppEnvironment.PROD.value)

        all {
            dimension = "env"
        }
    }
}

fun ApplicationExtension.configureBuildType() {
    buildTypes {
        getByName(AppEnvironment.DEBUG.value) {
            signingConfig = signingConfigs.getByName(AppEnvironment.DEBUG.value)
        }

        //getByName(AppEnvironment.RELEASE.value) {
        //    signingConfig = signingConfigs.getByName(AppEnvironment.RELEASE.value)
        //    isMinifyEnabled = true
        //    isShrinkResources = true
        //}

        all {  }
    }
}

fun ApplicationExtension.configureAppSuffix() {
    productFlavors {
        named(AppEnvironment.DEV.value) {
            versionNameSuffix = "-debug"
            applicationIdSuffix = ".debug"
        }
        named(AppEnvironment.QA.value) {
            versionNameSuffix = "-qa"
            applicationIdSuffix = ".qa"
        }
        named(AppEnvironment.PROD.value) {  }
    }
}
