package com.musabapps.colorfultodos.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao {
    @Insert
    suspend fun addTodo(todo: TodoEntity)

    @Query("SELECT * FROM todotable")
    suspend fun getAllTodo(): List<TodoEntity>

    @Delete
    suspend fun deleteTodo(todo: TodoEntity)
}