package com.yarn.services

import com.fasterxml.jackson.databind.*
import com.yarn.services.routing.*
import io.ktor.application.*
import io.ktor.auth.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.jackson.*
import io.ktor.response.*
import io.ktor.routing.get
import io.ktor.routing.routing

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(Authentication) {
    }

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
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

    routing {
        get("/") {
            logger.info("HELLO, WORLD!")
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }

        user()
    }
}

