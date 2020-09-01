package com.musabapps.colorfultodos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.musabapps.colorfultodos.Repository.TodoRepository

class TodoListFragmentViewModelFactory(val repo: TodoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoListFragmentViewModel::class.java)) {
            return TodoListFragmentViewModel(repo) as T
        }
        throw IllegalArgumentException("Viewmode")
    }
}

class TodoListFragmentViewModel(private val repo: TodoRepository) : ViewModel() {

    private val _viewState = MutableLiveData<TodoListFragmentViewState>()
    val viewState: LiveData<TodoListFragmentViewState> = _viewState

    fun loadData() {
        _viewState.value = repo.loadTodo()
    }

}