package com.alfontetarqui.fasktapp.adapter

import android.text.Editable
import androidx.recyclerview.widget.RecyclerView
import com.alfontetarqui.fasktapp.databinding.ItemFreenoteBinding
import com.alfontetarqui.fasktapp.models.FreeNoteModel

class FreeNotesViewHolder(private val binding: ItemFreenoteBinding,
                          private val onItemClick: (FreeNoteModel) -> Unit,
                          private val onItemLongClick: (FreeNoteModel, Int) -> Unit) : RecyclerView.ViewHolder(binding.root) {

    fun render(freeNoteModel: FreeNoteModel) {
        binding.ItemBtnFreeNote.text = freeNoteModel.title
        binding.textDate.text = freeNoteModel.date
        binding.root.setOnClickListener { onItemClick(freeNoteModel) }
        binding.root.setOnLongClickListener {
            onItemLongClick(freeNoteModel, adapterPosition)
            true
        }
    }
}

