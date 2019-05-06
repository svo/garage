package `is`.qual.repository

import `is`.qual.model.Vehicle
import `is`.qual.model.VehicleType

open class VehicleRepository {
    open fun getAll(): List<Vehicle> {
        return listOf(Vehicle(VehicleType.CAR))
    }
}
