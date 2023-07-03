package com.tset.releasemanager.service

import com.tset.releasemanager.dto.ServiceDto
import com.tset.releasemanager.repository.DeploymentRepository
import com.tset.releasemanager.repository.ServiceDeployedRepository
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class ReleaseManagerServiceTest {

    val deploymentRepository: DeploymentRepository = mockk(relaxed =  true)
    val serviceDeployedRepository: ServiceDeployedRepository = mockk(relaxed =  true)
    private val releaseManagerService = ReleaseManagerService(deploymentRepository, serviceDeployedRepository )

    @Test
    fun repositoryTest() {
        releaseManagerService.deploy(ServiceDto("name", 1))
        verify { deploymentRepository.findFirstByOrderBySystemVersionDesc() }
        verify { deploymentRepository.findByDeployedServices_ServiceNameAndDeployedServices_Version("name", 1) }

        releaseManagerService.getServicesForVersionNumber(1)
        verify { deploymentRepository.findBySystemVersion(1) }
    }

}
