package `is`.qual.model

import `is`.qual.marshall.JsonData

enum class VehicleType {
    CAR, MOTORCYCLE
}

data class Vehicle(val type: VehicleType) : JsonData
