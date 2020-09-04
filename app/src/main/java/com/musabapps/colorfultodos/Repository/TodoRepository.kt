package com.musabapps.colorfultodos.Repository

import com.musabapps.colorfultodos.TodoListFragmentViewState
import com.musabapps.colorfultodos.database.TodoDao


class TodoRepository(private val todoDao: TodoDao) {

    suspend fun loadTodo(): TodoListFragmentViewState {
        return TodoListFragmentViewState(
            todoList = todoDao.getAllTodo()
        )
    }
}