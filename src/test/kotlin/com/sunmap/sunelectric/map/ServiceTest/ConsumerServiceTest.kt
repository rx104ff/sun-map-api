package com.sunmap.sunelectric.map.ServiceTest

import com.nhaarman.mockito_kotlin.*
import com.sunmap.sunelectric.map.enums.SolarPlan
import com.sunmap.sunelectric.map.models.ConsumerAccount
import com.sunmap.sunelectric.map.repositories.ConsumerAccountRepository
import com.sunmap.sunelectric.map.services.ConsumerService
import com.sunmap.sunelectric.map.utils.ConsumerAccountBuilder
import com.sunmap.sunelectric.map.utils.ConsumerAccountDTOBuilder
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
    fun getConsumerByMssl_getsConsumer() {
        val expctedConsumerAccount = ConsumerAccountBuilder().default()
        whenever(consumerAccountRepository.findByMssl(expctedConsumerAccount.mssl!!)).thenReturn(expctedConsumerAccount)

        val consumerAccount = consumerService.getConsumerByMssl(expctedConsumerAccount.mssl!!)

        verify(consumerAccountRepository).findByMssl(expctedConsumerAccount.mssl!!)
        Assertions.assertThat(consumerAccount.address).isEqualTo(expctedConsumerAccount.address)
        Assertions.assertThat(consumerAccount.solarPlan).isEqualTo(expctedConsumerAccount.solarPlan.toString())
    }

    @Test
    fun getAllConsumers_getsConsumers() {
        val expctedConsumerAccountOne = ConsumerAccount(1, "145 Robison, Singapore", SolarPlan.SolarLITE)
        val expctedConsumerAccountTwo = ConsumerAccount(2, "290 Robison, Singapore", SolarPlan.SolarPEAK)
        whenever(consumerAccountRepository.findAll()).thenReturn(listOf(expctedConsumerAccountOne, expctedConsumerAccountTwo))

        val consumerAccounts = consumerService.getAllConsumers()

        verify(consumerAccountRepository).findAll()
        Assertions.assertThat(consumerAccounts[0].address).isEqualTo(expctedConsumerAccountOne.address)
        Assertions.assertThat(consumerAccounts[0].solarPlan).isEqualTo(expctedConsumerAccountOne.solarPlan.toString())
        Assertions.assertThat(consumerAccounts[1].address).isEqualTo(expctedConsumerAccountTwo.address)
        Assertions.assertThat(consumerAccounts[1].solarPlan).isEqualTo(expctedConsumerAccountTwo.solarPlan.toString())

    }

    @Test
    fun saveNewConsumer_savesConsumer() {
        val expectedConsumerAccount = ConsumerAccountBuilder().default()
        val consumerAccountDto = ConsumerAccountDTOBuilder().default()
        whenever(consumerAccountRepository.save(ConsumerAccount.fromDto(consumerAccountDto))).thenReturn(expectedConsumerAccount)

        val consumerAccount = consumerService.saveNewConsumer(consumerAccountDto)

        verify(consumerAccountRepository).save(ConsumerAccount.fromDto(consumerAccountDto))
        Assertions.assertThat(consumerAccount.address).isEqualTo(expectedConsumerAccount.address)
        Assertions.assertThat(consumerAccount.solarPlan).isEqualTo(expectedConsumerAccount.solarPlan)
        Assertions.assertThat(consumerAccount.mssl).isEqualTo(expectedConsumerAccount.mssl)
    }

    @Test
    fun updateConsumer_updateConsumer() {
        val solarPlan = SolarPlan.SolarLITE
        val originalConsumerAccount = ConsumerAccountBuilder().default()
        val updatedConsumerAccount = ConsumerAccountBuilder(solarPlan = solarPlan).default()

        whenever(consumerAccountRepository.findByMssl(originalConsumerAccount.mssl)).thenReturn(originalConsumerAccount)
        whenever(consumerAccountRepository.save(updatedConsumerAccount)).thenReturn(updatedConsumerAccount)

        val consumerAccount = consumerService.updateConsumerPlan(originalConsumerAccount.mssl!!, solarPlan)

        verify(consumerAccountRepository).save(updatedConsumerAccount)
        verify(consumerAccountRepository).findByMssl(originalConsumerAccount.mssl)
        Assertions.assertThat(consumerAccount.id).isEqualTo(updatedConsumerAccount.id)
        Assertions.assertThat(consumerAccount.solarPlan).isEqualTo(updatedConsumerAccount.solarPlan)
        Assertions.assertThat(consumerAccount.mssl).isEqualTo(updatedConsumerAccount.mssl)
    }

    @Test
    fun removeConsumer_removesConsumer() {
        val consumerAccount = ConsumerAccountBuilder().default()
        whenever(consumerAccountRepository.removeByMssl(consumerAccount.mssl)).thenReturn(1)

        consumerService.removeConsumerByMssl(consumerAccount.mssl!!)

        verify(consumerAccountRepository).removeByMssl(consumerAccount.mssl)
    }
}