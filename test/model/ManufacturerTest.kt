package `is`.qual.model

import kotlin.test.Test
import kotlin.test.assertEquals

class ManufacturerTest {
    @Test
    fun shouldHaveExpectedManufacturerType() {
        val expected = "mitsubishi"
        assertEquals(expected, Manufacturer(expected).name)
    }
}
