package com.tset.releasemanager.controller

import com.tset.releasemanager.dto.ServiceDto
import com.tset.releasemanager.service.ReleaseManagerService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController


@RestController
class ReleaseManagerController(private val releaseManagerService: ReleaseManagerService) {

    @PostMapping("/deploy", consumes= ["application/json"])
    fun deployService(@RequestBody service: ServiceDto): Int{
        return releaseManagerService.deploy(service)
    }

    @GetMapping("/services")
    fun getServicesForVersionNumber(@RequestParam("systemVersion") systemVersion: Int): MutableList<ServiceDto>{
        return releaseManagerService.getServicesForVersionNumber(systemVersion)
    }


}