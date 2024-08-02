package com.alfontetarqui.fasktapp.adapter

import androidx.recyclerview.widget.RecyclerView
import com.alfontetarqui.fasktapp.databinding.ItemNoteGroupsBinding
import com.alfontetarqui.fasktapp.models.GroupNoteModel

class GroupNotesViewHolder (private val binding: ItemNoteGroupsBinding,
private val onItemClick: (GroupNoteModel) -> Unit,
private val onItemLongClick: (GroupNoteModel, Int) -> Unit) : RecyclerView.ViewHolder(binding.root) {

    fun render(groupNoteModel: GroupNoteModel) {
        binding.ItemBtnGroupNote.text = groupNoteModel.title
        binding.textDate.text = groupNoteModel.date
        binding.root.setOnClickListener { onItemClick(groupNoteModel) }
        binding.root.setOnLongClickListener {
            onItemLongClick(groupNoteModel, adapterPosition)
            true
        }
    }
}

