package com.sunmap.sunelectric.map.ControllerTest

import com.sunmap.sunelectric.map.models.CityInformation
import com.sunmap.sunelectric.map.repositories.CityInformationRepository
import com.sunmap.sunelectric.map.utils.CityInformationBuilder
import com.sunmap.sunelectric.map.utils.Helper
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
import org.springframework.transaction.annotation.Transactional

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class CityInformationControllerTest {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var cityInformationRepository: CityInformationRepository

    @Test
    fun getCityInformaiton_ByCityName() {
        cityInformationRepository.deleteAll()
        val expectedCity = "Singapore"
        val expectedGlobalInformation = cityInformationRepository.save(CityInformation(name = expectedCity))

        mockMvc
                .perform(MockMvcRequestBuilders.get("/city/${expectedCity}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", Matchers.`is`(expectedGlobalInformation.name!!.toString())))
    }

    @Test
    fun saveCityInformation() {
        val expectedCity = CityInformationBuilder().default()

        mockMvc
                .perform(MockMvcRequestBuilders.post("/city/${expectedCity.name}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Helper.serializeToJson(expectedCity.name!!)))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.successMessage", Matchers.`is`(expectedCity.name!! + " has been saved")))
    }

    @Test
    fun removeCityInformation() {
        val expectedCity = cityInformationRepository.save(CityInformationBuilder().default())

        mockMvc
                .perform(MockMvcRequestBuilders.put("/city/${expectedCity.name}")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Helper.serializeToJson(expectedCity.name!!)))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.successMessage", Matchers.`is`(expectedCity.name!! + " has been removed")))
    }
}
