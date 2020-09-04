package com.musabapps.colorfultodos

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.musabapps.colorfultodos.Repository.TodoRepository

class TodoListFragmentViewModelFactory(
    private val repo: TodoRepository
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoListFragmentViewModel::class.java)) {
            return TodoListFragmentViewModel(repo) as T
        }
        throw IllegalArgumentException("Viewmodel is unknown")
    }
}

class TodoListFragmentViewModel(
    private val repo: TodoRepository
) : ViewModel() {
    val viewState: LiveData<TodoListFragmentViewState> = liveData {
        emit(repo.loadTodo())
    }

}