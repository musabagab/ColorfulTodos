package com.musabapps.colorfultodos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.musabapps.colorfultodos.Repository.TodoRepository

class TodoListFragmentViewModel(private val repo: TodoRepository) : ViewModel() {

    private val _viewState = MutableLiveData<TodoListFragmentViewState>()
    val viewState: LiveData<TodoListFragmentViewState> = _viewState

    fun loadData() {
        _viewState.value = repo.loadTodo()
    }

}