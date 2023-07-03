package com.tset.releasemanager.service

import com.tset.releasemanager.dto.ServiceDto
import com.tset.releasemanager.entities.Deployment
import com.tset.releasemanager.entities.ServiceDeployed
import com.tset.releasemanager.repository.DeploymentRepository
import com.tset.releasemanager.repository.ServiceDeployedRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ReleaseManagerService(
    val deploymentRepository: DeploymentRepository,
    val serviceDeployedRepository: ServiceDeployedRepository
) {

    @Transactional
    fun deploy(serviceDto: ServiceDto): Int {
        val lastDeployment = deploymentRepository.findFirstByOrderBySystemVersionDesc()
        var systemVersion = lastDeployment?.systemVersion ?: 0
        val services = lastDeployment?.deployedServices ?: emptyList()
        val deployment = deploymentRepository.findByDeployedServices_ServiceNameAndDeployedServices_Version(
            serviceDto.name ?: "", serviceDto.version ?: 0
        )
        if (deployment == null) {
            val service =
                serviceDeployedRepository.save(
                    ServiceDeployed(
                        serviceName = serviceDto.name,
                        version = serviceDto.version
                    )
                )
            deploymentRepository.save(
                Deployment(
                    systemVersion = ++systemVersion,
                    deployedServices = services.plus(service).toMutableList()
                )
            )
        }

        return systemVersion
    }

    fun getServicesForVersionNumber(systemVersion: Int): MutableList<ServiceDto> {
        return deploymentRepository.findBySystemVersion(systemVersion)?.deployedServices
            .orEmpty()
            .map { ServiceDto(it.serviceName, it.version) }
            .toMutableList()
    }

}