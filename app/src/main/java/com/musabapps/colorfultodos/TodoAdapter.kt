package com.musabapps.colorfultodos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.musabapps.colorfultodos.Model.Todo

class TodoViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {
    private val todoText = view.findViewById<TextView>(R.id.todoText)
    fun bind(todo: Todo) {
        todoText.text = todo.text
    }
}

class TodoAdapter(
    private val clickHandler: (Todo) -> Unit

) : ListAdapter<Todo, TodoViewHolder>(DIFF_CONFIG) {

    companion object {
        val DIFF_CONFIG = object : DiffUtil.ItemCallback<Todo>() {
            override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: Todo,
                newItem: Todo
            ): Boolean {
                return oldItem == newItem
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.todo_recyler_item, parent, false)
        return TodoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, postion: Int) {
        holder.bind(getItem(postion))
        holder.itemView.setOnClickListener {
            clickHandler(getItem(postion))
        }
    }

}