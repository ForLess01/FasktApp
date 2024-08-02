package com.alfontetarqui.fasktapp.models

class TasksMainProvider {
    companion object {
        val tasksMainListModels: MutableList<TasksMainModel> = mutableListOf(
            TasksMainModel("Math",
                "P1",
                "Academic",
                "03/08/2024","12:00",
                "3",
                "https://www.google.com",
                "Busqueda de informacion para el informe..."),
            TasksMainModel("Science",
                "P2",
                "Academic",
                "03/08/2024","12:00",
                "3",
                "https://www.google.com",
                "Busqueda de informacion para el informe..."),
            TasksMainModel("English",
                "P3",
                "Academic",
                "03/08/2024","12:00",
                "3",
                "https://www.google.com",
                "Busqueda de informacion para el informe...")
        )
    }
}