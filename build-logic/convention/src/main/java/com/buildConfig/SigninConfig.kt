package com.buildConfig

import com.android.build.api.dsl.CommonExtension
import java.io.File
import java.util.Properties

fun CommonExtension<*, *, *, *, *, *>.initSigninConfig(rootProjectDir: File) {
    signingConfigs {
        getByName(AppEnvironment.DEBUG.value) {  }

        create(AppEnvironment.RELEASE.value) {
            val properties = Properties().apply {
                runCatching {
                    load(File(rootProjectDir, "signing/keystore.properties").inputStream())
                }.onFailure {
                    println("Failed to load release keystore properties")
                }
            }

            storeFile = File(rootProjectDir, "signinConfig/keystore.jks")
            keyAlias = properties["keyAlias"] as? String
            keyPassword = properties["keyPassword"] as? String
            storePassword = properties["storePassword"] as? String
        }
    }
}
