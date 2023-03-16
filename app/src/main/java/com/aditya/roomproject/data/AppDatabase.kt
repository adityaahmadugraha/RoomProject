package com.aditya.roomproject.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.aditya.roomproject.data.dao.UserDao
import com.aditya.roomproject.data.entity.User


@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) requireNotNull() {
                instance =
                    Room.databaseBuilder(context, AppDatabase::class.java, "app-database")
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .build()
            }


            return instance!!
        }
    }
}