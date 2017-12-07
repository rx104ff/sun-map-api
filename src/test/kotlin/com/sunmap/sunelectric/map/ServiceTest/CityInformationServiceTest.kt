package com.sunmap.sunelectric.map.ServiceTest

import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.sunmap.sunelectric.map.models.CityInformation
import com.sunmap.sunelectric.map.repositories.CityInformationRepository
import com.sunmap.sunelectric.map.services.CityInformationService
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
class CityInformationServiceTest {
    @Mock
    lateinit var cityInformationRepository: CityInformationRepository

    @InjectMocks
    lateinit var cityInformationService: CityInformationService

    @Test
    fun getAllInformation_getsAllInformation() {
        val expectedCityInformation = CityInformation(1, "Singapore")
        whenever(cityInformationRepository.findByName(expectedCityInformation.name!!)).thenReturn(expectedCityInformation)

        val globalInformation = cityInformationService.getCityInformationByName(expectedCityInformation.name!!)

        verify(cityInformationRepository).findByName(expectedCityInformation.name!!)
        Assertions.assertThat(globalInformation.name).isEqualTo(expectedCityInformation.name)
    }

}