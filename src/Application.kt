package `is`.qual

import com.typesafe.config.ConfigFactory
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.gson.gson
import io.ktor.http.HttpStatusCode
import io.ktor.request.path
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing
import java.util.Properties
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.slf4j.event.Level
import `is`.qual.model.Vehicle
import `is`.qual.model.VehicleType
import `is`.qual.repository.VehicleRepository
import `is`.qual.repository.VehicleTable

fun main(args: Array<String>): Unit = io.ktor.server.jetty.EngineMain.main(args)

fun initializeDatabase() {
    val databaseType = ConfigFactory.load().getString("database_type")
    val configuration = ConfigFactory.load().getConfig(databaseType)
    val properties = Properties()
    configuration.entrySet().forEach { e -> properties.setProperty(e.key, configuration.getString(e.key)) }
    val hikariConfiguration = HikariConfig(properties)
    val datasource = HikariDataSource(hikariConfiguration)
    Database.connect(datasource)
}

fun Application.module(vehicleRepository: VehicleRepository = VehicleRepository()) {
    initializeDatabase()

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
            var result: List<Vehicle> = emptyList()

            transaction {
                SchemaUtils.create(VehicleTable)
                result = vehicleRepository.getAll()
            }

            call.respond(result)
        }

        post("/vehicle") {
            var result: Int = 0

            transaction {
                SchemaUtils.create(VehicleTable)
                result = vehicleRepository.save(Vehicle(VehicleType.CAR))
            }

            call.respond(HttpStatusCode.Created, result.toString())
        }
    }
}
