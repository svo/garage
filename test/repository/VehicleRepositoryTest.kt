package `is`.qual.repository

import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import kotlin.test.Test
import kotlin.test.assertEquals
import `is`.qual.model.Vehicle
import `is`.qual.model.VehicleType
import `is`.qual.repository.VehicleTable

class VehicleRepositoryTest {
    @Test
    fun shouldSaveVehicle() {
        val expected = 1

        Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")

        transaction {
            SchemaUtils.create(VehicleTable)
            assertEquals(expected, VehicleRepository().save(Vehicle(VehicleType.CAR)))
        }
    }

    @Test
    fun shouldHaveExpectedVehicles() {
        val expected = listOf(Vehicle(VehicleType.CAR))

        Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")

        transaction {
            SchemaUtils.create(VehicleTable)
            VehicleTable.insert { it[type] = VehicleType.CAR.name }

            assertEquals(expected, VehicleRepository().getAll())
        }
    }
}
