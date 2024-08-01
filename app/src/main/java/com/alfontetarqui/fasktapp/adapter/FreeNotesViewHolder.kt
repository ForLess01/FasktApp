package com.alfontetarqui.fasktapp.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alfontetarqui.fasktapp.R
import com.alfontetarqui.fasktapp.models.FreeNote

class FreeNotesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val FreeNote = view.findViewById<TextView>(R.id.ItemBtnFreeNote)

    fun render(freeNoteModel: FreeNote) {
        FreeNote.text = freeNoteModel.title
    }
}