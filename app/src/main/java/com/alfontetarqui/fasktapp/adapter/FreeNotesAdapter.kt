package com.alfontetarqui.fasktapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alfontetarqui.fasktapp.databinding.ItemFreenoteBinding
import com.alfontetarqui.fasktapp.models.FreeNoteModel

class FreeNotesAdapter(private val freeNotesListModel: MutableList<FreeNoteModel>) : RecyclerView.Adapter<FreeNotesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FreeNotesViewHolder {
        val binding = ItemFreenoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FreeNotesViewHolder(binding)
    }

    override fun getItemCount(): Int = freeNotesListModel.size

    override fun onBindViewHolder(holder: FreeNotesViewHolder, position: Int) {
        val item = freeNotesListModel[position]
        holder.render(item)
    }

    fun addFreeNoteModel(freeNoteModel: FreeNoteModel) {
        freeNotesListModel.add(freeNoteModel)
        notifyItemInserted(freeNotesListModel.size - 1)
    }
}
