package dev.antonius.todo.client.service

import dev.antonius.todo.entities.IdentifiableEntity
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

abstract class ResourceApiService<ID, R: IdentifiableEntity<ID>>(
    val baseUrl: String,
    /**
     * Route to the resource e.g. todos for http://localhost/todos/
     */
    val resource: String,
    /**
     * Because setBody needs a reified type parameter, we need to pass it as a closure.
     * @sample { setBody(it) }
     */
    val setResourceAsBody: HttpRequestBuilder.(R) -> Unit,
    /**
     * Because body() needs a reified type parameter, we need to pass it as a closure.
     * @sample { body() }
     */
    val getResourceAsBody: suspend HttpResponse.() -> List<R>,
    /**
     * Because body() needs a reified type parameter, we need to pass it as a closure.
     * @sample { body() }
     */
    val getIdAsBody: suspend HttpResponse.() -> ID,
    val client: HttpClient = defaultClient(),
) {
    suspend fun fetch(): List<R> = client.get("$baseUrl/todos").getResourceAsBody()

    suspend fun create(item: R): ID = client.post("$baseUrl/todos") {
        contentType(ContentType.Application.Json)
        setResourceAsBody(item)
    }.getIdAsBody()

    suspend fun put(item: R): Boolean = client.put("$baseUrl/todos/${item.id}") {
        contentType(ContentType.Application.Json)
        setResourceAsBody(item)
    }.status.run { this == HttpStatusCode.OK || this == HttpStatusCode.Created }

    suspend fun delete(item: R) = client.delete("$baseUrl/todos/${item.id}").status == HttpStatusCode.OK
}