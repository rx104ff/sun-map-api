package com.sunmap.sunelectric.map.ControllerTest

import com.nhaarman.mockito_kotlin.mock
import com.sunmap.sunelectric.map.enums.SolarPlan
import com.sunmap.sunelectric.map.repositories.ConsumerAccountRepository
import com.sunmap.sunelectric.map.utils.*
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
class ConsumerControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var consumerAccountRepository: ConsumerAccountRepository

    @Test
    fun getAllConsumers() {
        val consumerAccountOne = consumerAccountRepository.save(ConsumerAccountBuilder().default())
        val consumerAccountTwo = consumerAccountRepository.save(ConsumerAccountBuilder(address = "290 Robinson").default())

        mockMvc
                .perform(MockMvcRequestBuilders.get("/consumer")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].address", Matchers.`is`(consumerAccountOne.address)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].solarPlan", Matchers.`is`(consumerAccountOne.solarPlan!!.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].address", Matchers.`is`(consumerAccountTwo.address)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].solarPlan", Matchers.`is`(consumerAccountTwo.solarPlan!!.toString())))
    }

    @Test
    fun getConsumerByAddress() {
        val consumerAccount = consumerAccountRepository.save(ConsumerAccountBuilder().default())

        mockMvc
                .perform(MockMvcRequestBuilders.get("/consumer/email/${consumerAccount.address}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.address", Matchers.`is`(consumerAccount.address)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.solarPlan", Matchers.`is`(consumerAccount.solarPlan!!.toString())))

    }

    @Test
    fun getConsumerByMssl() {
        val consumerAccount = consumerAccountRepository.save(ConsumerAccountBuilder().default())

        mockMvc
                .perform(MockMvcRequestBuilders.get("/consumer/mssl/${consumerAccount.mssl}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.address", Matchers.`is`(consumerAccount.address)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.solarPlan", Matchers.`is`(consumerAccount.solarPlan!!.toString())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.mssl", Matchers.`is`(consumerAccount.mssl)))

    }

    @Test
    fun saveNewConsumer() {
        val consumerDTO = ConsumerAccountDTOBuilder().default()

        mockMvc
                .perform(MockMvcRequestBuilders.post("/consumer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(Helper.serializeToJson(consumerDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.successMessage", Matchers.`is`("Consumer is successfully saved")))

    }

    @Test
    fun updateConsumerPlan() {
        val consumerAccount = consumerAccountRepository.save(ConsumerAccountBuilder().default())
        val solarPlan = SolarPlan.SolarLITE

        mockMvc
                .perform(MockMvcRequestBuilders.put("/consumer/${consumerAccount.mssl}/$solarPlan")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.successMessage", Matchers.`is`("Consumer's plan is successfully updated")))

        mockMvc
                .perform(MockMvcRequestBuilders.get("/consumer/${consumerAccount.mssl}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.solarPlan", Matchers.`is`(solarPlan.toString())))
    }

    @Test
    fun deleteConsumer() {
        val consumerAccount = consumerAccountRepository.save(ConsumerAccountBuilder().default())

        mockMvc
                .perform(MockMvcRequestBuilders.put("/consumer/mssl/${consumerAccount.mssl}/")
                        .contentType((MediaType.APPLICATION_JSON)))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.successMessage", Matchers.`is`("Consumer is successfully deleted")))
    }
}