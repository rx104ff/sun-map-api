package com.sunmap.sunelectric.map.ServiceTest

import com.fasterxml.jackson.databind.jsontype.impl.AsExistingPropertyTypeSerializer
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.sunmap.sunelectric.map.models.ConsumerAccount
import com.sunmap.sunelectric.map.repositories.ConsumerAccountRepository
import com.sunmap.sunelectric.map.services.ConsumerService
import org.assertj.core.api.Assertions
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class ConsumerServiceTest {
    @Mock
    lateinit var consumerAccountRepository: ConsumerAccountRepository

    @InjectMocks
    lateinit var consumerService: ConsumerService

    @Test
    fun getConsumerByEmail_getsConsumer() {
        val expctedConsumerAccount = ConsumerAccount(1, "145 Robison, Singapore", "SolarFLEX")
        whenever(consumerAccountRepository.findByAddress(expctedConsumerAccount.address!!)).thenReturn(expctedConsumerAccount)

        val consumerAccount = consumerService.getConsumerByAddress(expctedConsumerAccount.address!!)

        verify(consumerAccountRepository).findByAddress(expctedConsumerAccount.address!!)
        Assertions.assertThat(consumerAccount.address).isEqualTo(expctedConsumerAccount.address)
        Assertions.assertThat(consumerAccount.solarPlan).isEqualTo(expctedConsumerAccount.solarPlan)

    }

    @Test
    fun getAllConsumers_getsConsumers() {
        val expctedConsumerAccountOne = ConsumerAccount(1, "145 Robison, Singapore", "SolarPEAK")
        val expctedConsumerAccountTwo = ConsumerAccount(2, "290 Robison, Singapore", "SolarLIT")
        whenever(consumerAccountRepository.findAll()).thenReturn(listOf(expctedConsumerAccountOne, expctedConsumerAccountTwo))

        val consumerAccounts = consumerService.getAllConsumers()

        verify(consumerAccountRepository).findAll()
        Assertions.assertThat(consumerAccounts[0].address).isEqualTo(expctedConsumerAccountOne.address)
        Assertions.assertThat(consumerAccounts[0].solarPlan).isEqualTo(expctedConsumerAccountOne.solarPlan)
        Assertions.assertThat(consumerAccounts[1].address).isEqualTo(expctedConsumerAccountTwo.address)
        Assertions.assertThat(consumerAccounts[1].solarPlan).isEqualTo(expctedConsumerAccountTwo.solarPlan)

    }
}