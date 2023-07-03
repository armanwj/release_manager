package com.tset.releasemanager.repository

import com.tset.releasemanager.entities.ServiceDeployed
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ServiceDeployedRepository : JpaRepository<ServiceDeployed, Long>