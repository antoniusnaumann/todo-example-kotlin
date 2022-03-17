package dev.antonius.todo.server.service

import dev.antonius.todo.entities.TodoItem

class TodoService {
    sealed class PutResult {
        class Created(val item: TodoItem): PutResult()
        class Updated(val item: TodoItem): PutResult()
    }

    val todos: List<TodoItem> get() = _todos
    private val _todos: MutableList<TodoItem> = mutableListOf()

    fun postTodo(item: TodoItem): Int {
        // A bit hacky but okay
        val index = todos.maxOf { it.id } + 1

        _todos.add(item.copy(id = index))

        return index
    }

    fun putTodo(item: TodoItem): PutResult {
        val index = _todos.indexOfFirst { it.id == item.id }

        return if (index != -1) {
            _todos[index] = item
            PutResult.Updated(item)
        } else {
            _todos.add(item)
            PutResult.Created(item)
        }
    }

    fun getTodo(id: Int): TodoItem? = _todos.firstOrNull { it.id == id }

    /**
     * @return true if the item was deleted successfully, false otherwise
     */
    fun deleteTodo(id: Int) = _todos.removeIf { it.id == id }
}