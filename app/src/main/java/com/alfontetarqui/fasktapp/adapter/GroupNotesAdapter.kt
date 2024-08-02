package com.alfontetarqui.fasktapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alfontetarqui.fasktapp.databinding.ItemNoteGroupsBinding
import com.alfontetarqui.fasktapp.models.GroupNoteModel
import com.alfontetarqui.fasktapp.models.GroupNotesProvider

class GroupNotesAdapter(
    private val groupNotesListModel: MutableList<GroupNoteModel>,
    private val onItemClick: (GroupNoteModel) -> Unit,
    private val onItemLongClick: (GroupNoteModel, Int) -> Unit
) : RecyclerView.Adapter<GroupNotesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroupNotesViewHolder {
        val binding =
            ItemNoteGroupsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GroupNotesViewHolder(binding, onItemClick, onItemLongClick)
    }

    override fun getItemCount(): Int = groupNotesListModel.size

    override fun onBindViewHolder(holder: GroupNotesViewHolder, position: Int) {
        val item = groupNotesListModel[position]
        holder.render(item)
    }

    fun addGroupNoteModel(groupNoteModel: GroupNoteModel) {
        groupNotesListModel.add(groupNoteModel)
        notifyItemInserted(groupNotesListModel.size - 1)
    }
}