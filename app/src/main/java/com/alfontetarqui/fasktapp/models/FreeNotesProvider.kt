package com.alfontetarqui.fasktapp.models

class FreeNotesProvider {
    companion object {
        val freeNotesListModels: MutableList<FreeNoteModel> = mutableListOf(
            FreeNoteModel("Math", "01/01/2022 10:00 AM"),
            FreeNoteModel("Science", "01/01/2022 11:00 AM"),
            FreeNoteModel("English", "01/01/2022 12:00 PM")
        )
    }
}
