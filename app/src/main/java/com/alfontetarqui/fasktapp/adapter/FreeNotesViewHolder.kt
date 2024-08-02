package com.alfontetarqui.fasktapp.adapter

import android.text.Editable
import androidx.recyclerview.widget.RecyclerView
import com.alfontetarqui.fasktapp.databinding.ItemFreenoteBinding
import com.alfontetarqui.fasktapp.models.FreeNoteModel

class FreeNotesViewHolder(private val binding: ItemFreenoteBinding) : RecyclerView.ViewHolder(binding.root) {

    fun render(freeNoteModel: FreeNoteModel) {
        binding.ItemBtnFreeNote.text = Editable.Factory.getInstance().newEditable(freeNoteModel.title)
        binding.textDate.text = freeNoteModel.date
    }
}

