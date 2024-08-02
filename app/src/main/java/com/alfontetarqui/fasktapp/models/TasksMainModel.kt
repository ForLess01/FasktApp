package com.alfontetarqui.fasktapp.models

data class TasksMainModel (
    var title: String,
    var priority: String,
    val group: String,
    val date: String,
    val hour: String,
    val repetition: String,
    val link: String,
    val description: String

)