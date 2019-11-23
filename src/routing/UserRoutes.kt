package com.yarn.services.routing

import io.ktor.application.call
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post

fun Routing.user() {
    get("/user") {
        call.respondText("Getting a user!")
    }
    post("/user") {
        call.respondText("Saving a user!")
    }
    //call.respond(mapOf("hello" to "world"))
}