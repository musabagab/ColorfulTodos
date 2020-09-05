package com.musabapps.colorfultodos.CreateTodoFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.musabapps.colorfultodos.BaseFragment
import com.musabapps.colorfultodos.R
import com.musabapps.colorfultodos.database.TodoDatabase
import com.musabapps.colorfultodos.database.TodoEntity
import com.musabapps.colorfultodos.showToast
import kotlinx.coroutines.launch
import java.util.*


class CreateTodoFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment


        val view = inflater.inflate(R.layout.fragment_create_todo, container, false)

        val createButton = view.findViewById<Button>(R.id.createButton)
        val todoTitleField = view.findViewById<TextInputEditText>(R.id.todoTitleField)

        createButton.setOnClickListener {
            if (todoTitleField.text.toString().isEmpty()) {
                requireActivity().showToast("Field should not be empty!")
                return@setOnClickListener
            }


            val todoText = todoTitleField.text.toString().trim()
            val todo = TodoEntity(text = todoText, date = Date().toString())

            launch {
                val dao = TodoDatabase(requireActivity()).getTodoDao()

                dao.addTodo(todo)

                requireContext().showToast("Todo Added")

                // go back to the list fragment
                val action =
                    CreateTodoFragmentDirections.actionCreateTodoFragmentToTodosListFragment()
                findNavController().navigate(action)
            }

        }

        return view
    }

}
