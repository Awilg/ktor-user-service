package com.yarn.services

import com.fasterxml.jackson.databind.*
import com.yarn.services.data.*
import com.yarn.services.models.*
import com.yarn.services.routing.*
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.response.*
import io.ktor.routing.*
import kotlinx.coroutines.*
import org.bson.types.*
import org.litote.kmongo.*
import org.litote.kmongo.coroutine.*
import org.litote.kmongo.id.*
import org.litote.kmongo.id.jackson.*
import org.litote.kmongo.reactivestreams.*
import org.litote.kmongo.reactivestreams.KMongo

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(Authentication) {
    }

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
            registerModule(IdJacksonModule())
        }
    }


//    intercept(ApplicationCallPipeline.Features) {
//        val requestId = UUID.randomUUID()
//        logger.attach("req.Id", requestId.toString()) {
//            logger.info("Interceptor[start]")
//            proceed()
//            logger.info("Interceptor[end]")
//        }
//    }


    val client = KMongo.createClient().coroutine //use coroutine extension
    val database = client.getDatabase("Yarn-User") //normal java driver usage
    val userCol = database.getCollection<User>() //KMongo extension method

    val userDao = UserDao(userCol)

    routing {
        get("/") {
            logger.info("HELLO, WORLD!")
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        get("/get") {
            logger.info("HELLO, WORLD!")
            val user = userCol.findOne(User::key eq ObjectId("5dd9a2933eea1e4550f66e46").toId())
            user?.let { u -> call.respond(u) }
        }

        get("/test") {
            logger.info("WHAT IS GOING ON!!!!!!!")
            userDao.save(User(name = "james"))
            call.respondText("Success")
        }

        userRoutes()
    }
}

