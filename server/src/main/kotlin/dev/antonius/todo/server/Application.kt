package dev.antonius.todo.server

import dev.antonius.todo.entities.TodoItem
import dev.antonius.todo.server.routing.todos
import dev.antonius.todo.server.service.TodoService
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    val app = Instance(
        TodoService(),
    )

    app.todoService.putTodo(TodoItem(1, "Banana", "5x"))
    app.todoService.putTodo(TodoItem(2, "Apples", "Green"))
    app.todoService.putTodo(TodoItem(3, "Turn oven off", "..or your house is on fire"))

    // For larger projects, consider using a [HOCON file](https://ktor.io/docs/configurations.html#hocon-file)
    embeddedServer(Netty, port = 8080, host = "127.0.0.1") {
        install(ContentNegotiation) {
            json()
        }

        routing {
            get("/") {
                call.respondText("Hello, world!")
            }

            todos(app)
        }
    }.start(wait = true)

    // Wanna deploy? Take a look at this tutorial on how to [deploy to heroku](https://ktor.io/docs/eap/heroku.html)
}