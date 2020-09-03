package com.musabapps.colorfultodos.Repository

import com.musabapps.colorfultodos.TodoListFragmentViewState
import com.musabapps.colorfultodos.database.TodoDao


class TodoRepository(val todoDao: TodoDao) {
    fun loadTodo(): TodoListFragmentViewState {
        return TodoListFragmentViewState(
            todoList = todoDao.getAllTodo()
        )
    }
}