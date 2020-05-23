package com.yarn.services

import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.yarn.services.controllers.KodeinController
import com.yarn.services.controllers.UserController
import com.yarn.services.data.UserDao
import com.yarn.services.models.User
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.auth.Authentication
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.http.ContentType
import io.ktor.jackson.jackson
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import org.kodein.di.Instance
import org.kodein.di.Kodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.singleton
import org.kodein.di.jvmType
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.id.jackson.IdJacksonModule
import org.litote.kmongo.reactivestreams.KMongo

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

fun Application.module() {
    install(Authentication) {
    }

    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
            registerModule(IdJacksonModule())
            registerModule(JavaTimeModule())
        }
    }

    install(DefaultHeaders) {

    }

    // Connect to the DB
    val client = KMongo.createClient("mongodb://127.0.0.1:27017").coroutine //use coroutine extension
    val database = client.getDatabase("Yarn-User") //normal java driver usage
    val userCol = database.getCollection<User>() //KMongo extension method

    kodeinApplication {
        bind<UserDao>() with singleton { UserDao(userCol) }
        bindSingleton { UserController(it) }
    }

    routing {
        get("/") {
            call.respondText("HELLO WORLD!", contentType = ContentType.Text.Plain)
        }
    }
}

/**
 * Registers a [kodeinApplication] that that will call [kodeinMapper] for mapping stuff.
 * The [kodeinMapper] is a lambda that is in charge of mapping all the required.
 *
 * After calling [kodeinMapper], this function will search
 * for registered subclasses of [KodeinController], and will call their [KodeinController.registerRoutes] methods.
 */
fun Application.kodeinApplication(
    kodeinMapper: Kodein.MainBuilder.(Application) -> Unit = {}
) {
    val application = this

    /**
     * Creates a [Kodein] instance, binding the [Application] instance.
     * Also calls the [kodeInMapper] to map the Controller dependencies.
     */
    val kodein = Kodein {
        bind<Application>() with instance(application)
        kodeinMapper(this, application)
    }

    /**
     * Detects all the registered [KodeinController] and registers its routes.
     */
    routing {
        for (bind in kodein.container.tree.bindings) {
            val bindClass = bind.key.type.jvmType as? Class<*>?
            if (bindClass != null && KodeinController::class.java.isAssignableFrom(bindClass)) {
                val res by kodein.Instance(bind.key.type)
                println("Registering '$res' routes...")
                (res as KodeinController).apply { registerRoutes() }
            }
        }
    }
}

/**
 * Shortcut for binding singletons to the same type.
 */
inline fun <reified T : Any> Kodein.MainBuilder.bindSingleton(crossinline callback: (Kodein) -> T) {
    bind<T>() with singleton { callback(this@singleton.kodein) }
}
