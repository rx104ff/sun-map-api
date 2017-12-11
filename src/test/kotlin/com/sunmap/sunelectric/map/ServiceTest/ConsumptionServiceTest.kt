package com.sunmap.sunelectric.map.ServiceTest

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.sunmap.sunelectric.map.enums.Duration
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
class ConsumptionServiceTest {
    @Mock
    lateinit var consumptionRepository: ConsumptionRepository

    @InjectMocks
    lateinit var consumptionService: ConsumptionService

    @Test
    fun getMonthlyTotalConsumptionByCity() {
        val duration =  Duration.Month
        val cityName = "Singapore"
        val consumptionOne = ConsumptionBuilder(dateTime = LocalDateTime.now(), duration = duration).default()
        val consumptionTwo = ConsumptionBuilder(dateTime = LocalDateTime.now(), duration = duration).default()

        whenever(consumptionRepository.findByDurationAndCity(cityName, duration)).thenReturn(listOf(consumptionOne, consumptionTwo))

        val singaporeMonthlyConsumption = consumptionService.getTotalMonthlyConsumptionByCity(cityName)

        verify(consumptionRepository).findByDurationAndCity(cityName, duration)
        Assertions.assertThat(singaporeMonthlyConsumption.toLong())
                .isEqualTo(consumptionOne.totalConsumption!!.plus(consumptionTwo.totalConsumption!!))
    }

    @Test
    fun getMonthlySolarConsumptionByCity() {
        val duration =  Duration.Month
        val cityName = "Singapore"
        val consumptionOne = ConsumptionBuilder(dateTime = LocalDateTime.now(), duration = duration).default()
        val consumptionTwo = ConsumptionBuilder(dateTime = LocalDateTime.now(), duration = duration).default()

        whenever(consumptionRepository.findByDurationAndCity(cityName, duration)).thenReturn(listOf(consumptionOne, consumptionTwo))

        val singaporeMonthlyConsumption = consumptionService.getSolarMonthlyConsumptionByCity(cityName)

        verify(consumptionRepository).findByDurationAndCity(cityName, duration)
        Assertions.assertThat(singaporeMonthlyConsumption.toLong())
                .isEqualTo(consumptionOne.solarConsumption!!.plus(consumptionTwo.solarConsumption!!))
    }

    @Test
    fun getYearlyTotalConsumptionByCity() {
        val duration =  Duration.Year
        val cityName = "Singapore"
        val consumptionOne = ConsumptionBuilder(dateTime = LocalDateTime.now(), duration = duration).default()
        val consumptionTwo = ConsumptionBuilder(dateTime = LocalDateTime.now(), duration = duration).default()

        whenever(consumptionRepository.findByDurationAndCity(cityName, duration)).thenReturn(listOf(consumptionOne, consumptionTwo))

        val singaporeYearlyConsumption = consumptionService.getTotalYearlyConsumptionByCity(cityName)

        verify(consumptionRepository).findByDurationAndCity(cityName, duration)
        Assertions.assertThat(singaporeYearlyConsumption.toLong())
                .isEqualTo(consumptionOne.totalConsumption!!.plus(consumptionTwo.totalConsumption!!))
    }

    @Test
    fun getYearlySolarConsumptionByCity() {
        val duration =  Duration.Year
        val cityName = "Singapore"
        val consumptionOne = ConsumptionBuilder(dateTime = LocalDateTime.now(), duration = duration).default()
        val consumptionTwo = ConsumptionBuilder(dateTime = LocalDateTime.now(), duration = duration).default()

        whenever(consumptionRepository.findByDurationAndCity(cityName, duration)).thenReturn(listOf(consumptionOne, consumptionTwo))

        val singaporeYearlyConsumption = consumptionService.getSolarYearlyConsumptionByCity(cityName)

        verify(consumptionRepository).findByDurationAndCity(cityName, duration)
        Assertions.assertThat(singaporeYearlyConsumption.toLong())
                .isEqualTo(consumptionOne.solarConsumption!!.plus(consumptionTwo.solarConsumption!!))
    }

    @Test
    fun getHourlyotalConsumptionByCity() {
        val duration =  Duration.Hour
        val cityName = "Singapore"
        val consumptionOne = ConsumptionBuilder(dateTime = LocalDateTime.now(), duration = duration).default()
        val consumptionTwo = ConsumptionBuilder(dateTime = LocalDateTime.now(), duration = duration).default()

        whenever(consumptionRepository.findByDurationAndCity(cityName, duration)).thenReturn(listOf(consumptionOne, consumptionTwo))

        val singaporeHourlyConsumption = consumptionService.getTotalHourlyConsumptionByCity(cityName)

        verify(consumptionRepository).findByDurationAndCity(cityName, duration)
        Assertions.assertThat(singaporeHourlyConsumption.toLong())
                .isEqualTo(consumptionOne.totalConsumption!!.plus(consumptionTwo.totalConsumption!!))
    }

    @Test
    fun getHourlySolarConsumptionByCity() {
        val duration =  Duration.Hour
        val cityName = "Singapore"
        val consumptionOne = ConsumptionBuilder(dateTime = LocalDateTime.now(), duration = duration).default()
        val consumptionTwo = ConsumptionBuilder(dateTime = LocalDateTime.now(), duration = duration).default()

        whenever(consumptionRepository.findByDurationAndCity(cityName, duration)).thenReturn(listOf(consumptionOne, consumptionTwo))

        val singaporeHourlyConsumption = consumptionService.getSolarHourlyConsumptionByCity(cityName)

        verify(consumptionRepository).findByDurationAndCity(cityName, duration)
        Assertions.assertThat(singaporeHourlyConsumption.toLong())
                .isEqualTo(consumptionOne.solarConsumption!!.plus(consumptionTwo.solarConsumption!!))
    }

    @Test
    fun getDailyTotalConsumptionByCity() {
        val duration =  Duration.Day
        val cityName = "Singapore"
        val consumptionOne = ConsumptionBuilder(dateTime = LocalDateTime.now(), duration = duration).default()
        val consumptionTwo = ConsumptionBuilder(dateTime = LocalDateTime.now(), duration = duration).default()

        whenever(consumptionRepository.findByDurationAndCity(cityName, duration)).thenReturn(listOf(consumptionOne, consumptionTwo))

        val singaporeDailyConsumption = consumptionService.getTotalDailyConsumptionByCity(cityName)

        verify(consumptionRepository).findByDurationAndCity(cityName, duration)
        Assertions.assertThat(singaporeDailyConsumption.toLong())
                .isEqualTo(consumptionOne.totalConsumption!!.plus(consumptionTwo.totalConsumption!!))
    }

    @Test
    fun getDailySolarConsumptionByCity() {
        val duration =  Duration.Day
        val cityName = "Singapore"
        val consumptionOne = ConsumptionBuilder(dateTime = LocalDateTime.now(), duration = duration).default()
        val consumptionTwo = ConsumptionBuilder(dateTime = LocalDateTime.now(), duration = duration).default()

        whenever(consumptionRepository.findByDurationAndCity(cityName, duration)).thenReturn(listOf(consumptionOne, consumptionTwo))

        val singaporeDailyConsumption = consumptionService.getSolarDailyConsumptionByCity(cityName)

        verify(consumptionRepository).findByDurationAndCity(cityName, duration)
        Assertions.assertThat(singaporeDailyConsumption.toLong())
                .isEqualTo(consumptionOne.solarConsumption!!.plus(consumptionTwo.solarConsumption!!))
    }
}
