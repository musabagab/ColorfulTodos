package com.musabapps.colorfultodos.TodoListFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.musabapps.colorfultodos.R
import com.musabapps.colorfultodos.database.TodoEntity

class TodoViewHolder(
    view: View
) : RecyclerView.ViewHolder(view) {
    private val todoText = view.findViewById<TextView>(R.id.todoText)
    private val createdDateText = view.findViewById<TextView>(R.id.createdDateText)

    fun bind(todo: TodoEntity) {
        todoText.text = todo.text
        createdDateText.text = todo.date
    }
}

class TodoAdapter(
    private val clickHandler: (TodoEntity) -> Unit

) : ListAdapter<TodoEntity, TodoViewHolder>(
    DIFF_CONFIG
) {

    companion object {
        val DIFF_CONFIG = object : DiffUtil.ItemCallback<TodoEntity>() {
            override fun areItemsTheSame(oldItem: TodoEntity, newItem: TodoEntity): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(
                oldItem: TodoEntity,
                newItem: TodoEntity
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.todo_recyler_item, parent, false)
        return TodoViewHolder(
            itemView
        )
    }

    override fun onBindViewHolder(holder: TodoViewHolder, postion: Int) {
        holder.bind(getItem(postion))
        holder.itemView.setOnClickListener {
            clickHandler(getItem(postion))
        }
    }

}