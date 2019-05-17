package `is`.qual.repository

import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.Table
import `is`.qual.model.Vehicle
import `is`.qual.model.VehicleType

const val SHORT_STRING_LENGTH = 16

object VehicleTable : Table("vehicle") {
    val id = integer("id").autoIncrement().primaryKey()
    val type = varchar("type", SHORT_STRING_LENGTH)
}

open class VehicleRepository {

    open fun getAll(): List<Vehicle> {
        return VehicleTable.selectAll().map {
            Vehicle(VehicleType.valueOf(it[VehicleTable.type]))
        }
    }

    open fun save(vehicle: Vehicle): Int {
        return VehicleTable.insert {
            it[type] = vehicle.type.name
        } get VehicleTable.id
    }
}
