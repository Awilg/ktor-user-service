package com.yarn.services.controllers

import com.yarn.services.data.UserDao
import com.yarn.services.models.User
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.response.respondText
import io.ktor.routing.Routing
import io.ktor.routing.get
import io.ktor.routing.post
import org.bson.types.ObjectId
import org.kodein.di.Kodein
import org.kodein.di.generic.instance
import org.litote.kmongo.eq
import org.litote.kmongo.id.toId

class UserController(kodein: Kodein) : KodeinController(kodein) {
    private val userDao : UserDao by instance()

    override fun Routing.registerRoutes() {
        get("/user") {
            val user = userDao.get("5dd9a2933eea1e4550f66e46")
            user?.let { u -> call.respond(u) }
        }
        post("/user") {
            userDao.save(User(name = "test_name"))
            call.respondText("Saving a user!")
        }
    }
}