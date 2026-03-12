package com.secrets.booksApi

import com.secrets.getLocalKey
import java.io.File

private const val BOOKS_API_CONFIG_FILE_NAME = "books-api.properties"
private const val BOOKS_API_KEY = "apiKey"

fun getBooksApiKey(projectRootDir: File) : String = getLocalKey(
    projectRootDir = projectRootDir,
    configFileName = BOOKS_API_CONFIG_FILE_NAME,
    configKeyName = BOOKS_API_KEY
).orEmpty()