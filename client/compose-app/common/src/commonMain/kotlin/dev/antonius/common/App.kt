package dev.antonius.common

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import dev.antonius.todo.entities.TodoItem
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.launch

@Composable
fun App() {
    var todos by remember { mutableStateOf(listOf<TodoItem>()) }
    val scope = rememberCoroutineScope()

    Column {
        Button(onClick = {
            scope.launch {
                todos = TodoItemService.fetchTodos()
            }
        }) {
            Text("Refresh")
        }

        todos.forEach {
            Text(it.title)
        }
    }
}

expect val localhost: String

/**
 * Please do not use a singleton for this in a real application and do not call this from your views directly.
 * Instead, build a view model for fragment-like view components which get their dependencies (like a TodoRepository)
 * injected through their constructor or with a dependency-injection framework.
 */
object TodoItemService {
    val client = HttpClient() {
        install(ContentNegotiation) {
            json()
        }
    }

    suspend fun fetchTodos(): List<TodoItem> = client.get("http://$localhost:8080/todos").body()
}
