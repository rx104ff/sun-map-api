package com.sunmap.sunelectric.map.ServiceTest

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.sunmap.sunelectric.map.enums.Duration
import com.sunmap.sunelectric.map.repositories.ConsumptionRepository
import com.sunmap.sunelectric.map.services.CityConsumptionService
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
class CityCityConsumptionServiceTest {
    @Mock
    lateinit var consumptionRepository: ConsumptionRepository

    @InjectMocks
    lateinit var cityConsumptionService: CityConsumptionService

    @Test
    fun getMonthlyTotalConsumptionByCity() {
        val duration = Duration.Month
        val cityName = "Singapore"
        val consumptionOne = ConsumptionBuilder(dateTime = LocalDateTime.now(), duration = duration).default()
        val consumptionTwo = ConsumptionBuilder(dateTime = LocalDateTime.now(), duration = duration).default()

        whenever(consumptionRepository.findTop24ByDurationAndCityOrderByDateTimeDesc(cityName, duration)).thenReturn(listOf(consumptionOne, consumptionTwo))

        val singaporeMonthlyConsumption = cityConsumptionService.getTotalMonthlyConsumptionByCity(cityName)

        verify(consumptionRepository).findTop24ByDurationAndCityOrderByDateTimeDesc(cityName, duration)
        Assertions.assertThat(singaporeMonthlyConsumption.toLong())
                .isEqualTo(consumptionOne.totalConsumption!!.plus(consumptionTwo.totalConsumption!!))
    }

    @Test
    fun getMonthlySolarConsumptionByCity() {
        val duration = Duration.Month
        val cityName = "Singapore"
        val consumptionOne = ConsumptionBuilder(dateTime = LocalDateTime.now(), duration = duration).default()
        val consumptionTwo = ConsumptionBuilder(dateTime = LocalDateTime.now(), duration = duration).default()

        whenever(consumptionRepository.findTop24ByDurationAndCityOrderByDateTimeDesc(cityName, duration)).thenReturn(listOf(consumptionOne, consumptionTwo))

        val singaporeMonthlyConsumption = cityConsumptionService.getSolarMonthlyConsumptionByCity(cityName)

        verify(consumptionRepository).findTop24ByDurationAndCityOrderByDateTimeDesc(cityName, duration)
        Assertions.assertThat(singaporeMonthlyConsumption.toLong())
                .isEqualTo(consumptionOne.solarConsumption!!.plus(consumptionTwo.solarConsumption!!))
    }

    @Test
    fun getYearlyTotalConsumptionByCity() {
        val duration = Duration.Year
        val cityName = "Singapore"
        val consumptionOne = ConsumptionBuilder(dateTime = LocalDateTime.now(), duration = duration).default()
        val consumptionTwo = ConsumptionBuilder(dateTime = LocalDateTime.now(), duration = duration).default()

        whenever(consumptionRepository.findByDurationAndCityOrderByDateTimeDesc(cityName, duration)).thenReturn(listOf(consumptionOne, consumptionTwo))

        val singaporeYearlyConsumption = cityConsumptionService.getTotalYearlyConsumptionByCity(cityName)

        verify(consumptionRepository).findByDurationAndCityOrderByDateTimeDesc(cityName, duration)
        Assertions.assertThat(singaporeYearlyConsumption.toLong())
                .isEqualTo(consumptionOne.totalConsumption!!.plus(consumptionTwo.totalConsumption!!))
    }

    @Test
    fun getYearlySolarConsumptionByCity() {
        val duration = Duration.Year
        val cityName = "Singapore"
        val consumptionOne = ConsumptionBuilder(dateTime = LocalDateTime.now(), duration = duration).default()
        val consumptionTwo = ConsumptionBuilder(dateTime = LocalDateTime.now(), duration = duration).default()

        whenever(consumptionRepository.findByDurationAndCityOrderByDateTimeDesc(cityName, duration)).thenReturn(listOf(consumptionOne, consumptionTwo))

        val singaporeYearlyConsumption = cityConsumptionService.getSolarYearlyConsumptionByCity(cityName)

        verify(consumptionRepository).findByDurationAndCityOrderByDateTimeDesc(cityName, duration)
        Assertions.assertThat(singaporeYearlyConsumption.toLong())
                .isEqualTo(consumptionOne.solarConsumption!!.plus(consumptionTwo.solarConsumption!!))
    }

    @Test
    fun getHourlyotalConsumptionByCity() {
        val duration = Duration.Hour
        val cityName = "Singapore"
        val consumptionOne = ConsumptionBuilder(dateTime = LocalDateTime.now(), duration = duration).default()
        val consumptionTwo = ConsumptionBuilder(dateTime = LocalDateTime.now(), duration = duration).default()

        whenever(consumptionRepository.findTop24ByDurationAndCityOrderByDateTimeDesc(cityName, duration)).thenReturn(listOf(consumptionOne, consumptionTwo))

        val singaporeHourlyConsumption = cityConsumptionService.getTotalHourlyConsumptionByCity(cityName)

        verify(consumptionRepository).findTop24ByDurationAndCityOrderByDateTimeDesc(cityName, duration)
        Assertions.assertThat(singaporeHourlyConsumption.toLong())
                .isEqualTo(consumptionOne.totalConsumption!!.plus(consumptionTwo.totalConsumption!!))
    }

    @Test
    fun getHourlySolarConsumptionByCity() {
        val duration = Duration.Hour
        val cityName = "Singapore"
        val consumptionOne = ConsumptionBuilder(dateTime = LocalDateTime.now(), duration = duration).default()
        val consumptionTwo = ConsumptionBuilder(dateTime = LocalDateTime.now(), duration = duration).default()

        whenever(consumptionRepository.findTop24ByDurationAndCityOrderByDateTimeDesc(cityName, duration)).thenReturn(listOf(consumptionOne, consumptionTwo))

        val singaporeHourlyConsumption = cityConsumptionService.getSolarHourlyConsumptionByCity(cityName)

        verify(consumptionRepository).findTop24ByDurationAndCityOrderByDateTimeDesc(cityName, duration)
        Assertions.assertThat(singaporeHourlyConsumption.toLong())
                .isEqualTo(consumptionOne.solarConsumption!!.plus(consumptionTwo.solarConsumption!!))
    }

    @Test
    fun getDailyTotalConsumptionByCity() {
        val duration = Duration.Day
        val cityName = "Singapore"
        val consumptionOne = ConsumptionBuilder(dateTime = LocalDateTime.now(), duration = duration).default()
        val consumptionTwo = ConsumptionBuilder(dateTime = LocalDateTime.now(), duration = duration).default()

        whenever(consumptionRepository.findTop30ByDurationAndCityOrderByDateTimeDesc(cityName, duration)).thenReturn(listOf(consumptionOne, consumptionTwo))

        val singaporeDailyConsumption = cityConsumptionService.getTotalDailyConsumptionByCity(cityName)

        verify(consumptionRepository).findTop30ByDurationAndCityOrderByDateTimeDesc(cityName, duration)
        Assertions.assertThat(singaporeDailyConsumption.toLong())
                .isEqualTo(consumptionOne.totalConsumption!!.plus(consumptionTwo.totalConsumption!!))
    }

    @Test
    fun getDailySolarConsumptionByCity() {
        val duration = Duration.Day
        val cityName = "Singapore"
        val consumptionOne = ConsumptionBuilder(dateTime = LocalDateTime.now(), duration = duration).default()
        val consumptionTwo = ConsumptionBuilder(dateTime = LocalDateTime.of(2010,1,12,20,20), duration = duration).default()

        whenever(consumptionRepository.findTop30ByDurationAndCityOrderByDateTimeDesc(cityName, duration)).thenReturn(listOf(consumptionOne, consumptionTwo))

        val singaporeDailyConsumption = cityConsumptionService.getSolarDailyConsumptionByCity(cityName)

        verify(consumptionRepository).findTop30ByDurationAndCityOrderByDateTimeDesc(cityName, duration)
        Assertions.assertThat(singaporeDailyConsumption.toLong())
                .isEqualTo(consumptionOne.solarConsumption!!.plus(consumptionTwo.solarConsumption!!))
    }
}
