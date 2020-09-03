package com.musabapps.colorfultodos.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TodoDao {
    @Insert
    fun addTodo(todo: TodoEntity)

    @Query("SELECT * FROM todotable")
    fun getAllTodo(): List<TodoEntity>

    @Delete
    fun deleteTodo(todo: TodoEntity)
}