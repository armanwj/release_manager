package com.tset.releasemanager.entities

import jakarta.persistence.*

@Entity
class ServiceDeployed(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var serviceName: String?,
    var version: Int?,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ServiceDeployed

        if (id != other.id) return false
        if (serviceName != other.serviceName) return false
        if (version != other.version) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (serviceName?.hashCode() ?: 0)
        result = 31 * result + (version ?: 0)
        return result
    }

    override fun toString(): String {
        return "DeployedService(id=$id, serviceName=$serviceName, version=$version)"
    }

}