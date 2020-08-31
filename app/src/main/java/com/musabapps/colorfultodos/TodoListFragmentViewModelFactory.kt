package com.musabapps.colorfultodos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.musabapps.colorfultodos.Repository.TodoRepository

class TodoListFragmentViewModelFactory(val repo: TodoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(TodoRepository::class.java)
            .newInstance(repo)
    }
}