package com.judedevs.translatorapp.translate.domain.translate

enum class TranslateError {
    SERVICE_UNAVAILABLE,
    CLIENT_ERROR,
    SERVER_ERROR,
    SERIALIZATION_ERROR,
    UNKNOWN_ERROR
}

class TranslateException(private val error: TranslateError): Exception(message = "An error occurred wile translating: $error")