package com.musabapps.colorfultodos.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.musabapps.colorfultodos.Model.Todo

class TodoRepository {

    private val _todoList = MutableLiveData<List<Todo>>()

    val todoList: LiveData<List<Todo>> = _todoList

    fun loadTodo() {
        _todoList.value = listOf(
            Todo(text = "Hello there"),
            Todo(text = "This is a Todo"),
            Todo(text = "Hi there"),
            Todo(text = "I am good")
        )
    }


}