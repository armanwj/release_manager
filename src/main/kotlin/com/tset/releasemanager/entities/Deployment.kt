package com.tset.releasemanager.entities

import jakarta.persistence.*

@Entity
class Deployment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    @Column(unique = true)
    var systemVersion: Int?,
    @ManyToMany(cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var deployedServices: MutableList<ServiceDeployed> = arrayListOf(),
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Deployment

        if (id != other.id) return false
        if (systemVersion != other.systemVersion) return false
        if (deployedServices != other.deployedServices) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (systemVersion ?: 0)
        result = 31 * result + deployedServices.hashCode()
        return result
    }

    override fun toString(): String {
        return "Deployment(id=$id, systemVersion=$systemVersion, deployedServices=$deployedServices)"
    }


}