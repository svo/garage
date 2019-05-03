package `is`.qual.model

import kotlin.test.Test
import kotlin.test.assertEquals

class VehicleTest {
    @Test
    fun shouldHaveExpectedVehicleType() {
        assertEquals(Vehicle(VehicleType.CAR).type, VehicleType.CAR)
    }
}
