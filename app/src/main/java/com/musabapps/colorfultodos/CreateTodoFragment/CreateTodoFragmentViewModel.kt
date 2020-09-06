package com.musabapps.colorfultodos.CreateTodoFragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.musabapps.colorfultodos.Repository.TodoRepository
import com.musabapps.colorfultodos.database.TodoEntity
import kotlinx.coroutines.launch
import java.util.*

class CreateTodoFragmentViewModelFactory(
    private val repo: TodoRepository
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateTodoFragmentViewModel::class.java)) {
            return CreateTodoFragmentViewModel(repo) as T
        }
        throw IllegalArgumentException("ViewModel is unknown")
    }
}

class CreateTodoFragmentViewModel(
    private val repo: TodoRepository
) : ViewModel() {
    fun addTodo(todoText: String) =
        viewModelScope.launch {
            repo.addTodo(TodoEntity(text = todoText, date = Date().toString()))
        }

}