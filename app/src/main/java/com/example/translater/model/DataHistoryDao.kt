package com.example.translater.model

import androidx.room.*

@Dao
interface DataHistoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(data: DataHistory)

    @Delete
    fun delete(data: DataHistory)

    @Query("SELECT * FROM history_table")
    suspend fun getAll(): List<DataHistory>
}