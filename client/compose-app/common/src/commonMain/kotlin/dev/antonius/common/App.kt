package dev.antonius.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.input.ImeAction
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

            AddItemView(viewModel::addTodo)
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

@Composable
fun AddItemView(add: (String) -> Unit) {
    var text by remember { mutableStateOf("") }

    val action: (String) -> Unit = { input ->
        text = if (input.contains('\n')) {
            add(text.trim('\n'))
            ""
        } else {
            input
        }
    }

    BasicTextField(text, action,
        Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .fillMaxWidth()
            .padding(12.dp),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
        keyboardActions = KeyboardActions(onSend = {
            add(text)
            text = ""
        }),
        singleLine = true,
    )
}
