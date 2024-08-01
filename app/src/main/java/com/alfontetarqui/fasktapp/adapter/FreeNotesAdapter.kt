package com.alfontetarqui.fasktapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alfontetarqui.fasktapp.R
import com.alfontetarqui.fasktapp.models.FreeNote

class FreeNotesAdapter(private val FreeNotesList: List<FreeNote>) : RecyclerView.Adapter<FreeNotesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FreeNotesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FreeNotesViewHolder(layoutInflater.inflate(R.layout.item_freenote, parent, false))
    }

    override fun getItemCount(): Int = FreeNotesList.size

    override fun onBindViewHolder(holder: FreeNotesViewHolder, position: Int) {
        val item = FreeNotesList[position]
        holder.render(item)
    }

}