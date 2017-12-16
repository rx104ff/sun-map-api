package com.sunmap.sunelectric.map.ServiceTest

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.sunmap.sunelectric.map.models.GeneratorAccount
import com.sunmap.sunelectric.map.repositories.GeneratorAccountRepository
import com.sunmap.sunelectric.map.services.GeneratorService
import com.sunmap.sunelectric.map.services.helpers.GeoCodeService
import com.sunmap.sunelectric.map.utils.ConsumerAccountBuilder
import com.sunmap.sunelectric.map.utils.GeneratorAccountBuilder
import com.sunmap.sunelectric.map.utils.GeneratorAccountDTOBuilder
import org.assertj.core.api.Assertions
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class GeneratorServiceTest {
    @Mock
    lateinit var generatorAccountRepository: GeneratorAccountRepository

    @Mock
    lateinit var geoCodeService: GeoCodeService

    @InjectMocks
    lateinit var generatorService: GeneratorService

    @Test
    fun getGeneratorByAddress_getsGenerator() {
        val consumerAccountOne = ConsumerAccountBuilder().default()
        val consumerAccountTwo = ConsumerAccountBuilder(address = "146 Robison, Singapore").default()
        val consumerAccountThree = ConsumerAccountBuilder(address = "147 Robison, Singapore").default()
        val expectedGeneratorAccount = GeneratorAccountBuilder().withConsumerAccounts(listOf(
                consumerAccountOne,
                consumerAccountTwo,
                consumerAccountThree))
        whenever(generatorAccountRepository.findByAddress(expectedGeneratorAccount.address)).thenReturn(expectedGeneratorAccount)

        val generatorAccount = generatorService.getGeneratorByAddress(expectedGeneratorAccount.address!!)

        verify(generatorAccountRepository).findByAddress(expectedGeneratorAccount.address!!)
        Assertions.assertThat(generatorAccount.address).isEqualTo(expectedGeneratorAccount.address)
        Assertions.assertThat(generatorAccount.consumerAddress).isEqualTo(listOf(consumerAccountOne.address, consumerAccountTwo.address, consumerAccountThree.address))
    }

    @Test
    fun getAllGenerators_getsGenerators() {
        val expctedGeneratorAccountOne = GeneratorAccount(1, "145 Robison, Singapore")
        val expctedGeneratorAccountTwo = GeneratorAccount(2, "290 Robison, Singapore")
        whenever(generatorAccountRepository.findAll()).thenReturn(listOf(expctedGeneratorAccountOne, expctedGeneratorAccountTwo))

        val generatorAccounts = generatorService.getAllGenerators()

        verify(generatorAccountRepository).findAll()
        Assertions.assertThat(generatorAccounts[0].address).isEqualTo(expctedGeneratorAccountOne.address)
        Assertions.assertThat(generatorAccounts[1].address).isEqualTo(expctedGeneratorAccountTwo.address)

    }

    @Test
    fun saveNewGenerator_savesNewGenerator() {
        val coordinates = listOf(103.81, 1.27)
        val expectedGeneratorAccount = GeneratorAccountBuilder().withCoordinates(coordinates)
        val generatorAccountDto = GeneratorAccountDTOBuilder().withCoordinates(coordinates)
        whenever(geoCodeService.getCoordinates(generatorAccountDto.address!!)).thenReturn(coordinates)
        whenever(generatorAccountRepository.save(GeneratorAccount.fromDto(generatorAccountDto))).thenReturn(expectedGeneratorAccount)

        val generatorAccount = generatorService.saveNewGenerator(generatorAccountDto)

        verify(generatorAccountRepository).save(GeneratorAccount.fromDto(generatorAccountDto))
//        Assertions.assertThat(generatorAccount.mapCoordinates).isEqualTo(expectedGeneratorAccount.mapCoordinates)
        Assertions.assertThat(generatorAccount.address).isEqualTo(expectedGeneratorAccount.address)
    }

    @Test
    fun removeGenerator_removesGenerator() {
        val generatorAccount = GeneratorAccountBuilder(id = 1).default()
        whenever(generatorAccountRepository.removeById(generatorAccount.id)).thenReturn(1)

        generatorService.removeGeneratorById(generatorAccount.id!!)

        verify(generatorAccountRepository).removeById(generatorAccount.id)
    }

    @Test
    fun getConsumers_ByGenerator_getsConsumer() {
        val consumerAccountOne = ConsumerAccountBuilder().default()
        val consumerAccountTwo = ConsumerAccountBuilder(address = "146 Robison, Singapore").default()
        val consumerAccountThree = ConsumerAccountBuilder(address = "147 Robison, Singapore").default()
        val generatorAccount = GeneratorAccountBuilder().withConsumerAccounts(listOf(
                consumerAccountOne,
                consumerAccountTwo,
                consumerAccountThree))
        whenever(generatorAccountRepository.findByAddress(generatorAccount.address)).thenReturn(generatorAccount)


    }
}
