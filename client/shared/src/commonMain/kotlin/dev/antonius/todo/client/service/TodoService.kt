package dev.antonius.todo.client.service

import dev.antonius.todo.entities.TodoItem
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*

class TodoItemService(
    baseUrl: String,
    client: HttpClient = defaultClient()
): ResourceApiService<Int, TodoItem>(baseUrl, "todos", { setBody(it) }, { body() }, { body() }, client)
