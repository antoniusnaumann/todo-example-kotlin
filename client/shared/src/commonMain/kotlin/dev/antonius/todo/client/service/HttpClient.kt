package dev.antonius.todo.client.service

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.serialization.kotlinx.json.*

fun defaultClient(): HttpClient = HttpClient {
    install(ContentNegotiation) {
        json()
    }
}