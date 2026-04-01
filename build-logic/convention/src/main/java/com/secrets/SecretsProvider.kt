package com.secrets

import java.io.File
import java.io.FileInputStream
import java.util.Properties

internal fun getLocalKey(projectRootDir: File, configFileName: String, configKeyName: String): String? {
    val configFilePath = "$projectRootDir/$configFileName"
    val configFile = File(configFilePath)
    return if (!configFile.exists()) {
        println("Local file(${configFilePath}) doesn't exist!")
        println("If you want to use local config, create a file with next path:\n$configFilePath")
        null
    } else {
        readKeyFromFile(configFile, configKeyName)
    }
}

private fun readKeyFromFile(file: File, configKeyName: String): String {
    val properties = Properties().also { it.load(FileInputStream(file)) }
    val key = properties.getProperty(configKeyName)
    check(!key.isNullOrEmpty()) {
        "File key [$configKeyName] in file [${file.name}] is either not presented or empty!"
    }
    return key
}

internal fun getKeyFromEnvironment(keyName: String): String = System.getenv().getOrElse(keyName) {
    error("$keyName key isn't presented in ENV config!")
}
