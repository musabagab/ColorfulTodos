package com.musabapps.colorfultodos.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.musabapps.colorfultodos.Model.Todo
import org.joda.time.DateTime
import java.util.*


class TodoRepository {

    private val _todoList = MutableLiveData<List<Todo>>()

    val todoList: LiveData<List<Todo>> = _todoList


    fun loadTodo() {
        _todoList.value = listOf(
            Todo(text = "This is a Todo", date = getDate()),
            Todo(text = "Hi there", date = getDate()),
            Todo(text = "I am good", date = getDate())
        )
    }

    fun getDate(): String {
        val juDate = Date()
        val dt = DateTime(juDate)

        val month = dt.monthOfYear // where January is 1 and December is 12

        val day = dt.dayOfMonth

        return "$day, $month"
    }


}