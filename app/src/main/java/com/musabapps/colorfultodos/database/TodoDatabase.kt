package com.musabapps.colorfultodos.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [TodoEntity::class],
    version = 1
)

abstract class TodoDatabase() : RoomDatabase() {

    abstract fun getTodoDao(): TodoDao

    companion object {
        @Volatile
        private var instance: TodoDatabase? = null

        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { database ->
                instance = database
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext, TodoDatabase::class.java,
                "tododatabase"
            ).build()
    }
}