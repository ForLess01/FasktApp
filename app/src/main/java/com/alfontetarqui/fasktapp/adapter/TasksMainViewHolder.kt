package com.alfontetarqui.fasktapp.adapter

import androidx.recyclerview.widget.RecyclerView
import com.alfontetarqui.fasktapp.databinding.ItemTaskMainBinding
import com.alfontetarqui.fasktapp.models.GroupNoteModel
import com.alfontetarqui.fasktapp.models.TasksMainModel

class TasksMainViewHolder (private val binding: ItemTaskMainBinding,
private val onItemClick: (TasksMainModel) -> Unit,
private val onItemLongClick: (TasksMainModel, Int) -> Unit) : RecyclerView.ViewHolder(binding.root){
    fun render(tasksMainModel: TasksMainModel) {
        binding.ItemBtnGroupNote.text = tasksMainModel.title
        binding.Priority.text = tasksMainModel.priority
        binding.root.setOnClickListener { onItemClick(tasksMainModel) }
        binding.root.setOnLongClickListener {
            onItemLongClick(tasksMainModel, adapterPosition)
            true
        }
    }
}