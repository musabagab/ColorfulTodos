package com.musabapps.colorfultodos.CreateTodoFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputEditText
import com.musabapps.colorfultodos.R
import com.musabapps.colorfultodos.Repository.TodoRepository
import com.musabapps.colorfultodos.database.TodoDatabase
import com.musabapps.colorfultodos.showToast


class CreateTodoFragment : Fragment() {

    private lateinit var viewModelFactory: CreateTodoFragmentViewModelFactory
    private val viewModel: CreateTodoFragmentViewModel by viewModels(
        factoryProducer = {
            viewModelFactory
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_create_todo, container, false)

        val createButton = view.findViewById<Button>(R.id.createButton)
        val todoTitleField = view.findViewById<TextInputEditText>(R.id.todoTitleField)

        context?.let {
            val databaseDao = TodoDatabase(it).getTodoDao()
            viewModelFactory = CreateTodoFragmentViewModelFactory(TodoRepository(databaseDao))
        }

        createButton.setOnClickListener {
            if (todoTitleField.text.toString().isEmpty()) {
                requireActivity().showToast("Field should not be empty!")
                return@setOnClickListener
            }
            // insert it to the database
            viewModel.addTodo(todoTitleField.text.toString().trim())
            requireContext().showToast("Todo Added")
            // go back to the list fragment
            val action =
                CreateTodoFragmentDirections.actionCreateTodoFragmentToTodosListFragment()
            findNavController().navigate(action)

        }

        return view
    }

}
