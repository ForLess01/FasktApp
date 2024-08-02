package com.alfontetarqui.fasktapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alfontetarqui.fasktapp.databinding.ItemTaskMainBinding
import com.alfontetarqui.fasktapp.models.TasksMainModel

class TasksMainAdapter (
    private val tasksMainList: MutableList<TasksMainModel>,
    private val onItemClick: (TasksMainModel) -> Unit,
    private val onItemLongClick: (TasksMainModel, Int) -> Unit
) : RecyclerView.Adapter<TasksMainAdapter.TasksMainViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksMainViewHolder {
        val binding =
            ItemTaskMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TasksMainViewHolder(binding, onItemClick, onItemLongClick)
    }

    override fun getItemCount(): Int = tasksMainList.size

    override fun onBindViewHolder(holder: TasksMainViewHolder, position: Int) {
        val item = tasksMainList[position]
        holder.render(item)
    }

    fun addTasksMainModel(tasksMainModel: TasksMainModel) {
        tasksMainList.add(tasksMainModel)
        notifyItemInserted(tasksMainList.size - 1)
    }

    class TasksMainViewHolder(
        private val binding: ItemTaskMainBinding,
        private val onItemClick: (TasksMainModel) -> Unit,
        private val onItemLongClick: (TasksMainModel, Int) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

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
}
