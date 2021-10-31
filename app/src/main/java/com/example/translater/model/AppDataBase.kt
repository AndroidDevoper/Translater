package com.example.translater.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DataHistory::class], version = 1, exportSchema = false)
abstract class AppDataBase: RoomDatabase() {
    abstract fun dataHistoryDao(): DataHistoryDao
}