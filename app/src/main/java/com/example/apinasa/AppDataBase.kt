package com.example.apinasa

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities=[User::class],version=1)
abstract class AppDataBase : RoomDatabase(){
    abstract fun userDao():UserDao

    companion object{
        private var INSTANCIA:AppDataBase?=null
        fun getDataBase(context: Context):AppDataBase{
            if(INSTANCIA == null){
                synchronized(this){
                    INSTANCIA = Room.databaseBuilder(
                        context,
                        AppDataBase::class.java,"user_database")
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCIA!!
        }
    }
}