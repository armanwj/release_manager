package com.tset.releasemanager.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.tset.releasemanager.dto.ServiceDto
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

@SpringBootTest
@AutoConfigureMockMvc
class ReleaseManagerControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun shouldDeployNewService() {
        val mvcResult = mockMvc.perform(
            MockMvcRequestBuilders.post("/deploy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(ServiceDto(name = "Service A", version = 1)))
        ).andReturn()
        Assertions.assertThat(mvcResult.response.status).isEqualTo(HttpStatus.OK.value())
        Assertions.assertThat(mvcResult.response.contentAsString).isEqualTo("1")
    }



    @Test
    fun shouldKeepDeploymentVersion() {
        val mvcResult = mockMvc.perform(
            MockMvcRequestBuilders.post("/deploy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(ServiceDto(name = "Service A", version = 1)))
        ).andReturn()
        Assertions.assertThat(mvcResult.response.status).isEqualTo(HttpStatus.OK.value())
        Assertions.assertThat(mvcResult.response.contentAsString).isEqualTo("1")

        val mvcResult1 = mockMvc.perform(
            MockMvcRequestBuilders.post("/deploy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(ServiceDto(name = "Service A", version = 1)))
        ).andReturn()
        Assertions.assertThat(mvcResult1.response.status).isEqualTo(HttpStatus.OK.value())
        Assertions.assertThat(mvcResult1.response.contentAsString).isEqualTo("1")
    }


    @Test
    fun shouldReturnServicesForVersionNumber() {
        val mvcResult = mockMvc.perform(
            MockMvcRequestBuilders.post("/deploy")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(ServiceDto(name = "Service A", version = 1)))
        ).andReturn()
        Assertions.assertThat(mvcResult.response.status).isEqualTo(HttpStatus.OK.value())
        Assertions.assertThat(mvcResult.response.contentAsString).isEqualTo("1")

        val mvcResult1 = mockMvc.perform(
            MockMvcRequestBuilders.get("/services?systemVersion=1")
        ).andReturn()
        Assertions.assertThat(mvcResult1.response.status).isEqualTo(HttpStatus.OK.value())
        Assertions.assertThat(mvcResult1.response.contentAsString.contains("Service A"))
        Assertions.assertThat(mvcResult1.response.contentAsString.contains("\"version\":1"))
    }


    fun asJsonString(obj: Any?): String {
        return try {
            ObjectMapper().writeValueAsString(obj)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

}