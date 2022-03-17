package dev.antonius.todo.client.service

import dev.antonius.todo.entities.TodoItem
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class TodoItemService(
    val baseUrl: String,
    val client: HttpClient = defaultClient()
) {
    suspend fun fetchTodos(): List<TodoItem> = client.get("$baseUrl/todos").body()
}