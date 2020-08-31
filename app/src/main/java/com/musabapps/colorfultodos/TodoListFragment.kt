package com.musabapps.colorfultodos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.musabapps.colorfultodos.Repository.TodoRepository
import com.musabapps.colorfultodos.databinding.FragmentTodosListBinding


class TodoListFragment : Fragment() {


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
        // Divider
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

        val factory = TodoListFragmentViewModelFactory(TodoRepository())
        val viewModel = factory.create(TodoListFragmentViewModel::class.java)

        // start observing
        viewModel.viewState.observe(viewLifecycleOwner, todoListObserver)
        // load the items
        viewModel.loadData()


        return binding.root
    }


}
