package com.musabapps.colorfultodos.TodoListFragment

import androidx.lifecycle.*
import com.musabapps.colorfultodos.Repository.TodoRepository
import com.musabapps.colorfultodos.database.TodoEntity
import kotlinx.coroutines.launch

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

    private val _viewState: MutableLiveData<TodoListFragmentViewState> = MutableLiveData()
    val viewState: LiveData<TodoListFragmentViewState> = _viewState

    init {
        loadTodoList()
    }

    fun deleteTodo(todoEntity: TodoEntity?) {
        viewModelScope.launch {
            repo.deleteTodo(todoEntity)
            // update the state
            _viewState.value?.let {
                val newList = it.todoList.toMutableList()
                newList.remove(todoEntity)
                // notify the listeners
                _viewState.value = TodoListFragmentViewState(todoList = newList)
            }

        }
    }

    private fun loadTodoList() {
        viewModelScope.launch {
            val loadState = repo.loadTodo()
            _viewState.value = loadState
        }
    }

}