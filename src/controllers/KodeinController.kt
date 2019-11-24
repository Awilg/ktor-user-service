package com.yarn.services.controllers

import io.ktor.application.*
import io.ktor.routing.*
import org.kodein.di.*
import org.kodein.di.generic.*

/**
 * A [KodeinAware] base class for Controllers handling routes.
 */
abstract class KodeinController(override val kodein: Kodein) : KodeinAware {
    /**
     * Injected dependency with the current [Application].
     */
    val application: Application by instance()

    /**
     * Method that subtypes must override to register the handled [Routing] routes.
     */
    abstract fun Routing.registerRoutes()
}
