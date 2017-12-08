package com.sunmap.sunelectric.map.ControllerTest

import com.sunmap.sunelectric.map.models.GlobalInformation
import com.sunmap.sunelectric.map.repositories.GlobalInformationRepository
import com.sunmap.sunelectric.map.utils.GlobalInformationDTOBuilder
import com.sunmap.sunelectric.map.utils.Helper
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
import org.hamcrest.Matchers

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class GlobalInformationControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var globalInformationRepository: GlobalInformationRepository

    @Test
    fun getGlobalInformation() {
        val expectedGlobalInformation = globalInformationRepository.save(GlobalInformation(totalConsumption = 1000, totalGeneration = 2000, carbonCredit = 20))

        mockMvc
                .perform(MockMvcRequestBuilders.get("/global")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalConsumption", Matchers.`is`(expectedGlobalInformation.totalConsumption!!.toInt())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalGeneration", Matchers.`is`(expectedGlobalInformation.totalGeneration!!.toInt())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.carbonCredit", Matchers.`is`(expectedGlobalInformation.carbonCredit!!.toInt())))
    }

    @Test
    fun saveGlobalInformation() {
        val globalInformationDTO = GlobalInformationDTOBuilder().default()

        mockMvc
                .perform(MockMvcRequestBuilders.post("/global")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Helper.serializeToJson(globalInformationDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.successMessage", Matchers.`is`("Global information successfully saved.")))
    }
}