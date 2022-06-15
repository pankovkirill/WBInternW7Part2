package com.example.wbinternw7part2.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

class App : Application() {
    @SuppressLint("StaticFieldLeak")
    object ContextHolder {
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        ContextHolder.context = this
    }
}