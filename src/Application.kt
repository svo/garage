package `is`.qual

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.gson.gson
import io.ktor.request.path
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.routing
import org.slf4j.event.Level
import `is`.qual.repository.VehicleRepository

fun main(args: Array<String>): Unit = io.ktor.server.jetty.EngineMain.main(args)

fun Application.module(vehicleRepository: VehicleRepository = VehicleRepository()) {
    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }

    install(DefaultHeaders)
    install(ContentNegotiation) {
        gson {
        }
    }
    routing {
        get("/vehicle") {
            call.respond(vehicleRepository.getAll())
        }
    }
}
