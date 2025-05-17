package com.example.proje

import android.content.Context
import android.provider.CalendarContract.Instances
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.proje.UserDatabase.Companion

@Database(entities = [Word::class], version = 1, exportSchema = false)
 abstract class WordDatabase:RoomDatabase() {
    abstract fun WordDao():WordDao
    companion object{

        @Volatile private var INSTANCE: WordDatabase?= null
        fun getDatabase(context: Context): WordDatabase {
            return WordDatabase.INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    WordDatabase::class.java,
                    "Word.db"
                )
                   // .createFromAsset("Word.db") // ← bu satır eklendi
                    .fallbackToDestructiveMigration()
                    .build()
            }
        }
    }
}