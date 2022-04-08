package com.fangzsx.retrofit_room.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.fangzsx.retrofit_room.model.Drink

@Database(entities = [Drink::class], version = 1)
abstract class DrinkDatabase : RoomDatabase() {

    abstract fun getDrinkDao() : DrinkDao

    //here, we create singleton instance of db

    companion object{
        @Volatile
        private var INSTANCE : DrinkDatabase? = null

        @Synchronized
        fun getInstance(context : Context) : DrinkDatabase{
            if(INSTANCE == null){
                INSTANCE = Room.databaseBuilder(
                    context,
                DrinkDatabase::class.java,
                "drink_db")
                    .build()
            }

            return INSTANCE as DrinkDatabase
        }
    }
}