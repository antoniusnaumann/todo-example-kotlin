package dev.antonius.common

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.antonius.todo.client.service.TodoItemService
import dev.antonius.todo.entities.TodoItem

expect val localhost: String

@Composable
fun App() {
    val viewModel = remember {
        TodoViewModel(TodoItemService("$localhost:8080"))
    }

    Column(
        Modifier.fillMaxWidth().padding(vertical = 64.dp, horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            viewModel.todos.forEach { item ->
                TodoItemView(item, viewModel::deleteTodo)
            }
        }

        Spacer(Modifier.weight(1f))

        Button(onClick = viewModel::fetchTodos) {
            Text("Refresh")
        }
    }
}

@Composable
fun TodoItemView(item: TodoItem, delete: (TodoItem) -> Unit) {
    Row {
        Column {
            Text(item.title, style = MaterialTheme.typography.titleMedium)
            Text(item.details, style = MaterialTheme.typography.bodyMedium)
        }

        Spacer(Modifier.weight(1f))

        IconButton(onClick = { delete(item) }) {
            Text("X", style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.error)
        }
    }
}
