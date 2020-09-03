package com.musabapps.colorfultodos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.musabapps.colorfultodos.Repository.TodoRepository
import com.musabapps.colorfultodos.database.TodoDatabase
import com.musabapps.colorfultodos.databinding.FragmentTodosListBinding
import kotlinx.coroutines.launch


class TodoListFragment : BaseFragment() {
    private lateinit var factory: TodoListFragmentViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentTodosListBinding.inflate(inflater)
        // Prepare the Recyclerview
        binding.createTodoFab.setOnClickListener {
            val action = TodoListFragmentDirections.actionTodosListFragmentToCreateTodoFragment()
            findNavController().navigate(action)
        }
        // prepare the recyclerview
        binding.todoRecycler.layoutManager = LinearLayoutManager(requireContext())
        val todoAdapter = TodoAdapter { clickedTodo ->
            Toast.makeText(requireContext(), clickedTodo.text, Toast.LENGTH_LONG).show()
        }
        binding.todoRecycler.adapter = todoAdapter
        // Divider item
        binding.todoRecycler.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )
        // create the observer
        val todoListObserver = Observer<TodoListFragmentViewState> { viewState ->
            todoAdapter.submitList(viewState.todoList)
        }

        context?.let {
            val databaseDao = TodoDatabase(it).getTodoDao()
            factory = TodoListFragmentViewModelFactory(TodoRepository(databaseDao))
        }
        val viewModel = factory.create(TodoListFragmentViewModel::class.java)

        // start observing
        viewModel.viewState.observe(viewLifecycleOwner, todoListObserver)
        // load the items
        launch {
            viewModel.loadData()
        }
        return binding.root
    }


}
