package com.example.apinasa

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @ColumnInfo(name="userName") var userName:String,
    @ColumnInfo(name="password") var password:String
){
    @PrimaryKey(autoGenerate = true) var id:Int = 0
}