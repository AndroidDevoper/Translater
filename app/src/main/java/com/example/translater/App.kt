package com.example.translater

import android.app.Application
import androidx.room.Room
import com.example.translater.model.AppDataBase

class App: Application() {
    companion object {
        lateinit var db: AppDataBase
    }

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(applicationContext,
            AppDataBase::class.java,
            "history").build()
    }
}