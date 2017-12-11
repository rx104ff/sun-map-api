package com.sunmap.sunelectric.map.ServiceTest

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.sunmap.sunelectric.map.repositories.GenerationRepository
import com.sunmap.sunelectric.map.services.GenerationService
import com.sunmap.sunelectric.map.utils.GenerationBuilder
import org.assertj.core.api.Assertions
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class GenerationServiceTest {
    @Mock
    lateinit var generationRepository: GenerationRepository

    @InjectMocks
    lateinit var generationService: GenerationService

    @Test
    fun getSolarGenerationByCity() {
        val cityName = "Singapore"
        val generationOne = GenerationBuilder(dateTime = LocalDateTime.now()).default()
        val generationTwo = GenerationBuilder(dateTime = LocalDateTime.now()).default()

        whenever(generationRepository.findAllByCity(cityName)).thenReturn(listOf(generationOne, generationTwo))

        val singaporeHourlyGeneration = generationService.getSolarHourlyGenerationByCity(cityName)

        verify(generationRepository).findAllByCity(cityName)
        Assertions.assertThat(singaporeHourlyGeneration.toLong())
                .isEqualTo(generationOne.solarGeneration!!.plus(generationTwo.solarGeneration!!))
    }
}