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
    fun deleteTodo(todoEntity: TodoEntity?) {
        viewModelScope.launch {
            repo.deleteTodo(todoEntity)
        }
    }

    fun refreshList(newList: MutableList<TodoEntity>) {
        viewState = liveData {
            emit(TodoListFragmentViewState(todoList = newList))
        }
    }

    fun removeItem(todoAdapter: TodoAdapter, adapterPosition: Int) {
        val currentList = todoAdapter.currentList.toMutableList()
        currentList.removeAt(adapterPosition)
        refreshList(currentList)
        todoAdapter.notifyItemRemoved(adapterPosition)
    }


    var viewState: LiveData<TodoListFragmentViewState> = liveData {
        emit(repo.loadTodo())
    }

}