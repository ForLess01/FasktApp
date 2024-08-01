package com.alfontetarqui.fasktapp.models

data class dtTasksModel(
    val name: String = "",
    val priority: Int = 0,
    val group: String = "",
    val details: String = "",
    val date: String = "",
    val hour: Int = 0,
    val repetition: Int = 0,
    val links: String = "",
    val status: Boolean = false,
    val id: Int = 0
)
