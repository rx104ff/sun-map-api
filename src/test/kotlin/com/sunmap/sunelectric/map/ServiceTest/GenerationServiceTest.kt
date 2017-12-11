package com.sunmap.sunelectric.map.ServiceTest

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.sunmap.sunelectric.map.repositories.ConsumptionRepository
import com.sunmap.sunelectric.map.services.ConsumptionService
import com.sunmap.sunelectric.map.utils.ConsumptionBuilder
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
    lateinit var consumptionRepository: ConsumptionRepository

    @InjectMocks
    lateinit var consumptionService: ConsumptionService

    @Test
    fun getSolarGenerationByCity() {
        val cityName = "Singapore"
        val consumptionOne = ConsumptionBuilder(dateTime = LocalDateTime.now()).default()
        val consumptionTwo = ConsumptionBuilder(dateTime = LocalDateTime.now()).default()

        whenever(consumptionRepository.findAllByCity(cityName)).thenReturn(listOf(consumptionOne, consumptionTwo))

        val singaporeHourlyConsumption = consumptionService.getSolarHourlyConsumptionByCity(cityName)

        verify(consumptionRepository).findAllByCity(cityName)
        Assertions.assertThat(singaporeHourlyConsumption.toLong())
                .isEqualTo(consumptionOne.hourlySolarConsumption!!.plus(consumptionTwo.hourlySolarConsumption!!))
    }
}