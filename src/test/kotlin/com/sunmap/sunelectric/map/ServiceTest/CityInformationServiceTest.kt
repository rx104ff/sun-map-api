package com.sunmap.sunelectric.map.ServiceTest

import com.nhaarman.mockito_kotlin.doNothing
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.sunmap.sunelectric.map.models.CityInformation
import com.sunmap.sunelectric.map.repositories.CityInformationRepository
import com.sunmap.sunelectric.map.services.CityInformationService
import com.sunmap.sunelectric.map.services.helpers.GeoCodeService
import com.sunmap.sunelectric.map.utils.CityInformationBuilder
import com.sunmap.sunelectric.map.utils.CityInformationDTOBuilder
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

    @Mock
    lateinit var geoCodeService: GeoCodeService

    @Test
    fun getAllInformation_getsAllInformation() {
        val expectedCityInformation = CityInformation(1, "Singapore")
        whenever(cityInformationRepository.findByName(expectedCityInformation.name!!)).thenReturn(expectedCityInformation)

        val globalInformation = cityInformationService.getCityInformationByName(expectedCityInformation.name!!)

        verify(cityInformationRepository).findByName(expectedCityInformation.name!!)
        Assertions.assertThat(globalInformation.name).isEqualTo(expectedCityInformation.name)
    }

        @Test
    fun saveCityInformation_savesCityInformation() {
        val cityInformationDTO = CityInformationDTOBuilder().default()
        val expectedCityInformation = CityInformationBuilder().default()
        whenever(cityInformationRepository.save(CityInformation.fromDto(cityInformationDTO))).thenReturn(expectedCityInformation)
        whenever(geoCodeService.getCoordinates(cityInformationDTO.name!!)).thenReturn(cityInformationDTO.mapCoordinates)
        val cityInformation = cityInformationService.saveCityInformation(cityInformationDTO)

        verify(cityInformationRepository).save(CityInformation.fromDto(cityInformationDTO))
        Assertions.assertThat(cityInformation.mapCoordinates).isEqualTo(expectedCityInformation.mapCoordinates)
        Assertions.assertThat(cityInformation.name).isEqualTo(expectedCityInformation.name)
    }

    @Test
    fun removeCityInformaitonByName_removesCityInformation() {
        val cityInformation = CityInformationBuilder().default()
        whenever(cityInformationRepository.removeByName(cityInformation.name)).thenReturn(1)

        val removeReturnValue = cityInformationService.removeCityInformatinByName(cityInformation.name!!)

        verify(cityInformationRepository).removeByName(cityInformation.name)
        Assertions.assertThat(removeReturnValue).isEqualTo(1)
    }

}