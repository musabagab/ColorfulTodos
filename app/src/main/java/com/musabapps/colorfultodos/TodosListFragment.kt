package com.musabapps.colorfultodos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass.
 */
class TodosListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_todos_list, container, false)

        view.findViewById<Button>(R.id.button).setOnClickListener {
            val action = TodosListFragmentDirections.actionTodosListFragmentToCreateTodoFragment()
            findNavController().navigate(action)
        }
        return view
    }

}
