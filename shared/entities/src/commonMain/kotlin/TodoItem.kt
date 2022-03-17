package dev.antonius.todo.entities

import kotlinx.serialization.*

@Serializable
data class TodoItem(
    val id: Int,
    val title: String,
    val details: String,
)