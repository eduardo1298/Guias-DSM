package com.example.firebasekotlincrud.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.firebasekotlincrud.dao.NotesDao
import com.example.firebasekotlincrud.entities.Notes


@Database(entities = [Notes::class], version = 1, exportSchema = false)
abstract class NotesRoomDatabase :RoomDatabase() {
    abstract fun notesDao(): NotesDao
    companion object {

        @Volatile
        private var INSTANCE: NotesRoomDatabase? = null
        fun getDatabase(context: Context): NotesRoomDatabase{

            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesRoomDatabase::class.java,
                    "notes_database"
                ).build()
                INSTANCE = instance

                instance
            }
        }
    }
}