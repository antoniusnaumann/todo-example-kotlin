package dev.antonius.todo.swiftui

import dev.antonius.todo.entities.TodoItem

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }

    val exampleTodoItem = TodoItem(42, "Make sure it works on every platform", "Works on ${Platform().platform}")
}