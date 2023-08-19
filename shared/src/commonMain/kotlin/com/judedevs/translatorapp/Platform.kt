package com.judedevs.translatorapp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform