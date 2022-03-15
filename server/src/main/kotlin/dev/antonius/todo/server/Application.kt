package dev.antonius.todo.server

import dev.antonius.todo.entities.TodoItem
import dev.antonius.todo.server.service.TodoService
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.plugins.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    val app = Instance(
        TodoService(),
    )

    app.todoService.putTodo(TodoItem(1, "Banana", "5x"))
    app.todoService.putTodo(TodoItem(2, "Apples", "Green"))
    app.todoService.putTodo(TodoItem(3, "Turn oven off", "..or your house is on fire"))

    // For real life use cases, you should probably use a [HOCON file](https://ktor.io/docs/configurations.html#hocon-file)
    embeddedServer(Netty, port = 8080) {
        install(ContentNegotiation) {
            json()
        }

        routing {
            get("/") {
                call.respondText("Hello, world!")
            }

            route("/todos") {
                get {
                    call.respond(app.todoService.todos)
                }

                put("{id}") {
                    val item = call.receiveOrNull<TodoItem>() ?: return@put call.respondText(
                        "Missing item", status = HttpStatusCode.BadRequest)
                    val status = when (app.todoService.putTodo(item)) {
                        is TodoService.Result.Created -> HttpStatusCode.Created
                        is TodoService.Result.Updated -> HttpStatusCode.OK
                    }
                    call.respondText("Todo item stored correctly", status = status)
                }

                get("{id}") {
                    call.parameters["id"]?.toIntOrNull()?.let { id ->
                        app.todoService.getTodo(id)?.let { item ->
                            call.respond(item)
                        }
                    } ?: call.respondText("No item with this id", status = HttpStatusCode.NotFound)
                }
            }
        }
    }.start(wait = true)
}