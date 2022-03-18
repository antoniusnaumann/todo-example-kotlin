package dev.antonius.todo.server.routing

import dev.antonius.todo.entities.TodoItem
import dev.antonius.todo.server.Instance
import dev.antonius.todo.server.service.TodoService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Routing.todos(app: Instance) = route("/todos") {
    get {
        call.respond(app.todoService.todos)
    }

    post {
        val item = call.receiveOrNull<TodoItem>() ?: return@post call.respondText(
            "Missing item", status = HttpStatusCode.BadRequest
        )

        val id = app.todoService.postTodo(item)

        call.respond(HttpStatusCode.Created, id)
    }

    put("{id}") {
        val item = call.receiveOrNull<TodoItem>() ?: return@put call.respondText(
            "Missing item", status = HttpStatusCode.BadRequest
        )
        val status = when (app.todoService.putTodo(item)) {
            is TodoService.PutResult.Created -> HttpStatusCode.Created
            is TodoService.PutResult.Updated -> HttpStatusCode.OK
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

    delete("{id}") {
        val id = call.parameters["id"]?.toIntOrNull() ?: return@delete call.respondText(
            "Provided ID not valid", status = HttpStatusCode.BadRequest
        )

        if (app.todoService.deleteTodo(id)) {
            call.respondText("Item deleted successfully", status = HttpStatusCode.OK)
        } else {
            call.respondText("No item with this id", status = HttpStatusCode.NotFound)
        }
    }
}