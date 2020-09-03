package com.musabapps.colorfultodos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.musabapps.colorfultodos.database.TodoDatabase
import com.musabapps.colorfultodos.database.TodoEntity
import kotlinx.android.synthetic.main.fragment_create_todo.*
import kotlinx.coroutines.launch
import java.util.*


class CreateTodoFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_todo, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        addButton.setOnClickListener {


            launch {
                val todo = TodoEntity(text = "My Next", date = Date().toString())
                requireContext().let {
                    TodoDatabase(it).getTodoDao().addTodo(todo)
                    Toast.makeText(
                        it,
                        "Todo saved",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }

        }
    }


}
