package com.sunmap.sunelectric.map.ServiceTest

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.sunmap.sunelectric.map.models.ConsumerAccount
import com.sunmap.sunelectric.map.models.GeneratorAccount
import com.sunmap.sunelectric.map.repositories.GeneratorAccountRepository
import com.sunmap.sunelectric.map.services.GeneratorService
import com.sunmap.sunelectric.map.utils.ConsumerAccountBuilder
import com.sunmap.sunelectric.map.utils.ConsumerAccountDTOBuilder
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

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class GeneratorServiceTest {
    @Mock
    lateinit var generatorAccountRepository: GeneratorAccountRepository

    @InjectMocks
    lateinit var generatorService: GeneratorService

    @Test
    fun getGeneratorByEmail_getsGenerator() {
        val expctedGeneratorAccount = GeneratorAccount(1, "145 Robison, Singapore")
        whenever(generatorAccountRepository.findByAddress(expctedGeneratorAccount.address!!)).thenReturn(expctedGeneratorAccount)

        val generatorAccount = generatorService.getGeneratorByAddress(expctedGeneratorAccount.address!!)

        verify(generatorAccountRepository).findByAddress(expctedGeneratorAccount.address!!)
        Assertions.assertThat(generatorAccount.address).isEqualTo(expctedGeneratorAccount.address)
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
        val expectedGeneratorAccount = GeneratorAccountBuilder().default()
        val generatorAccountDto = GeneratorAccountDTOBuilder().default()
        whenever(generatorAccountRepository.save(GeneratorAccount.fromDto(generatorAccountDto))).thenReturn(expectedGeneratorAccount)

        val generatorAccount = generatorService.saveNewGenerator(generatorAccountDto)

        verify(generatorAccountRepository).save(GeneratorAccount.fromDto(generatorAccountDto))
        Assertions.assertThat(generatorAccount.address).isEqualTo(expectedGeneratorAccount.address)
    }
}
