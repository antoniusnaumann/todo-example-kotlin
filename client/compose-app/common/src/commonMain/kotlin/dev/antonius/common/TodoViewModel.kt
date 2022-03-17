package dev.antonius.common

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import dev.antonius.todo.client.service.TodoItemService
import dev.antonius.todo.entities.TodoItem
import kotlinx.coroutines.launch

class TodoViewModel(
    private val todoService: TodoItemService
): PlatformViewModel() {
    var todos by mutableStateOf(listOf<TodoItem>())

    fun fetchTodos() = defaultScope.launch {
        todos = todoService.fetch()
    }

    fun deleteTodo(item: TodoItem) = defaultScope.launch {
        if (todoService.delete(item)) {
            todos = todos.toMutableList().apply { remove(item) }
        }
    }

    fun addTodo(title: String) = defaultScope.launch {
        val item = TodoItem(-1, title, "Created in Compose app")
        val id = todoService.create(item)

        todos = todos + item.copy(id = id)
    }
}