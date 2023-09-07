package com.example.apinasa

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM user_table")
    fun getAll():List<User>

    @Insert
    fun insert(user: User)
}