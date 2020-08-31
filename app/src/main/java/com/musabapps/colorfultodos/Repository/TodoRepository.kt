package com.musabapps.colorfultodos.Repository

import com.musabapps.colorfultodos.Model.Todo
import com.musabapps.colorfultodos.TodoListFragmentViewState
import org.joda.time.DateTime
import java.util.*


class TodoRepository {

    fun loadTodo(): TodoListFragmentViewState {
        return TodoListFragmentViewState(
            listOf(
                Todo(text = "This is a Todo", date = getDate()),
                Todo(text = "Hi there", date = getDate()),
                Todo(text = "I am good", date = getDate())
            )
        )
    }

    fun getDate(): String {
        val juDate = Date()
        val dt = DateTime(juDate)

        val monthName = android.text.format.DateFormat.format("MMM", juDate)
        val day = dt.dayOfMonth

        return "$day, $monthName"
    }


}