package com.musabapps.colorfultodos.TodoListFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.musabapps.colorfultodos.Repository.TodoRepository
import com.musabapps.colorfultodos.database.TodoDatabase
import com.musabapps.colorfultodos.databinding.FragmentTodosListBinding
import com.musabapps.colorfultodos.gone
import com.musabapps.colorfultodos.showToast
import com.musabapps.colorfultodos.visible


class TodoListFragment : Fragment() {

    private lateinit var viewModelFactory: TodoListFragmentViewModelFactory
    private val viewModel: TodoListFragmentViewModel by viewModels(
        factoryProducer = {
            viewModelFactory
        }
    )

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
        val todoAdapter =
            TodoAdapter { clickedTodo ->
                requireContext().showToast(clickedTodo.text)
            }
        binding.todoRecycler.adapter = todoAdapter
        // Divider item
        binding.todoRecycler.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                LinearLayoutManager.VERTICAL
            )
        )
        val itemTouchHelperCallback =
            object :
                ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    // get the swiped item
                    val deletedTodo = todoAdapter.currentList[viewHolder.adapterPosition]
                    // delete it from the database
                    viewModel.deleteTodo(deletedTodo)
                    // Undo Snackbar
                    Snackbar.make(binding.root, "Todo Deleted", Snackbar.LENGTH_LONG)
                        .setAction("Undo") {
                            viewModel.reInsertTodo(
                                deletedTodo,
                                todoAdapter.currentList.toMutableList()
                            )

                        }
                        .show()

                }
            }

        val itemTouchHelper = ItemTouchHelper(itemTouchHelperCallback)
        itemTouchHelper.attachToRecyclerView(binding.todoRecycler)
        context?.let {
            val databaseDao = TodoDatabase(it).getTodoDao()
            viewModelFactory = TodoListFragmentViewModelFactory(TodoRepository(databaseDao))
        }
        // create the observer
        val todoListObserver = Observer<TodoListFragmentViewState> { viewState ->
            if (viewState.todoList.isNotEmpty()) {
                binding.EmptyStateText.gone()
            } else {
                binding.EmptyStateText.visible()
            }
            binding.todoRecycler.visible()
            todoAdapter.submitList(viewState.todoList)
        }
        // start observing
        viewModel.viewState.observe(viewLifecycleOwner, todoListObserver)

        return binding.root
    }


}
