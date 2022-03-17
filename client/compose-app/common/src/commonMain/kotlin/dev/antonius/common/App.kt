package dev.antonius.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.antonius.todo.client.service.TodoItemService
import dev.antonius.todo.entities.TodoItem
import kotlinx.coroutines.launch

@Composable
fun App() {
    var todos by remember { mutableStateOf(listOf<TodoItem>()) }
    val scope = rememberCoroutineScope()

    Column(
        Modifier.fillMaxWidth().padding(vertical = 64.dp, horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            todos.forEach { TodoItemView(it) }
        }

        Spacer(Modifier.weight(1f))

        Button(onClick = {
            scope.launch {
                todos = Services.todoService.fetchTodos()
            }
        }) {
            Text("Refresh")
        }
    }
}

@Composable
fun TodoItemView(item: TodoItem) {
    Column {
        Text(item.title, style = MaterialTheme.typography.titleMedium)
        Text(item.details, style = MaterialTheme.typography.bodyMedium)
    }
}

expect val localhost: String

// Please instantiate those dependencies properly in your view models instead of doing this
object Services {
    val todoService = TodoItemService("$localhost:8080")
}
