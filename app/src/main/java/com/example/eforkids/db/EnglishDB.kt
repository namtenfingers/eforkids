package com.example.eforkids.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.eforkids.model.*


@Database(entities = [Topic::class, Word::class, StoryType::class, SubType::class,
                     Content::class, SubContent::class], version = 1)
abstract class EnglishDB : RoomDatabase() {
    abstract fun getEnglishDao(): EnglishDAO

    companion object {
        @Volatile
        private var INSTANCE: EnglishDB? = null

        fun getDatabase(context: Context): EnglishDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EnglishDB::class.java,
                    "KidsSoftwares.db"
                ).createFromAsset("db/KidsSoftwares.db").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}