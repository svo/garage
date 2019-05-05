package `is`.qual.model

import kotlin.test.Test
import kotlin.test.assertEquals

class VehicleTest {
    @Test
    fun shouldHaveExpectedVehicleType() {
        assertEquals(VehicleType.CAR, Vehicle(VehicleType.CAR).type)
    }
}
