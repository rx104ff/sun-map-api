package com.sunmap.sunelectric.map.ControllerTest

import com.sunmap.sunelectric.map.models.CityInformation
import com.sunmap.sunelectric.map.models.GlobalInformation
import com.sunmap.sunelectric.map.repositories.CityInformationRepository
import org.hamcrest.Matchers
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class CityInformationControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var cityInformationRepository: CityInformationRepository

    @Test
    fun getCityInformaiton_ByCityName() {
        val expectedCity = "Singapore"
        val expectedGlobalInformation = cityInformationRepository.save(CityInformation(name = expectedCity))

        mockMvc
                .perform(MockMvcRequestBuilders.get("/city/${expectedCity}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.`is`(expectedGlobalInformation.name!!.toString())))
    }
}
