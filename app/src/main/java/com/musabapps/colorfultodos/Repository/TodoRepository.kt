package com.musabapps.colorfultodos.Repository

import com.musabapps.colorfultodos.TodoListFragment.TodoListFragmentViewState
import com.musabapps.colorfultodos.database.TodoDao
import com.musabapps.colorfultodos.database.TodoEntity


class TodoRepository(private val todoDao: TodoDao) {

    suspend fun loadTodo(): TodoListFragmentViewState = TodoListFragmentViewState(
        todoList = todoDao.getAllTodo()
    )

    suspend fun deleteTodo(todoEntity: TodoEntity?) {
        todoEntity?.let { todo ->
            todoDao.deleteTodo(todo)
        }

    }
}