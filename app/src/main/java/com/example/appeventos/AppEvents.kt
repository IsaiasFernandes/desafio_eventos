package com.example.appeventos

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AppEvents : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}