package com.example.translater.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.translater.Language

@Entity(tableName = "history_table")
data class DataHistory (@PrimaryKey val value: String,
                        val from: Language,
                        val to: Language,
                        val result: String,
                        val date: Long)