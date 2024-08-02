package com.alfontetarqui.fasktapp.models

class GroupNotesProvider {
    companion object {
        val groupNotesListModels: MutableList<GroupNoteModel> = mutableListOf(
            GroupNoteModel("Math", "01/01/2022 10:00 AM"),
            GroupNoteModel("Science", "01/01/2022 11:00 AM"),
            GroupNoteModel("English", "01/01/2022 12:00 PM")
        )
    }
}