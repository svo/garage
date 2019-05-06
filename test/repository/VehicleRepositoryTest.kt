package `is`.qual.repository

import kotlin.test.Test
import kotlin.test.assertEquals
import `is`.qual.model.Vehicle
import `is`.qual.model.VehicleType

class VehicleRepositoryTest {
    @Test
    fun shouldHaveExpectedVehicle() {
        val expected = listOf(Vehicle(VehicleType.CAR))

        assertEquals(expected, VehicleRepository().getAll())
    }
}
