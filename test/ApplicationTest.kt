package `is`.qual

import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.doReturn
import kotlin.test.Test
import kotlin.test.assertEquals
import `is`.qual.model.Vehicle
import `is`.qual.model.VehicleType
import `is`.qual.repository.VehicleRepository

class ApplicationTest {
    @Test
    fun testVehicleGetStatus() {
        var expected: List<Vehicle> = emptyList()
        val vehicleRepository = mock<VehicleRepository> {
            on { getAll() } doReturn expected
        }

        withTestApplication({ module(vehicleRepository = vehicleRepository) }) {
            handleRequest(HttpMethod.Get, "/vehicle").apply {
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    @Test
    fun testVehicleGetResponse() {
        var expected: List<Vehicle> = listOf(Vehicle(VehicleType.CAR))
        val expectedResponse = "[{\"type\":\"CAR\"}]"
        val vehicleRepository = mock<VehicleRepository> {
            on { getAll() } doReturn expected
        }

        withTestApplication({ module(vehicleRepository = vehicleRepository) }) {
            handleRequest(HttpMethod.Get, "/vehicle").apply {
                assertEquals(expectedResponse, response.content)
            }
        }
    }
}
