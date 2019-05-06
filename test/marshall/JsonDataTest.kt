package `is`.qual.marshall

import kotlin.test.Test
import kotlin.test.assertEquals

data class MarshallTest(val name: String) : JsonData

class JsonDataTest {
    @Test
    fun shouldConvertDataClass() {
        assertEquals("{\"name\":\"test\"}", MarshallTest("test").toJson())
    }

    @Test
    fun shouldConvertJsonStringToDataClass() {
        assertEquals(MarshallTest("test"), "{\"name\":\"test\"}".toObject<MarshallTest>())
    }
}
