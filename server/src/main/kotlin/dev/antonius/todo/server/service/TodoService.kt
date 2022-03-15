package dev.antonius.todo.server.service

import dev.antonius.todo.entities.TodoItem

class TodoService {
    sealed class Result {
        class Created(val item: TodoItem): Result()
        class Updated(val item: TodoItem): Result()
    }


    val todos: List<TodoItem> get() = _todos
    private val _todos: MutableList<TodoItem> = mutableListOf()

    fun putTodo(item: TodoItem): Result {
        val index = _todos.indexOfFirst { it.id == item.id }

        return if (index != -1) {
            _todos[index] = item
            Result.Updated(item)
        } else {
            _todos.add(item)
            Result.Created(item)
        }
    }

    fun getTodo(id: Int): TodoItem? = _todos.firstOrNull { it.id == id }
}