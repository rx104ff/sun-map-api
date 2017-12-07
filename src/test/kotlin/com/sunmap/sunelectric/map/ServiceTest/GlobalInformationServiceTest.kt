package com.sunmap.sunelectric.map.ServiceTest

import com.nhaarman.mockito_kotlin.verify
import com.sunmap.sunelectric.map.repositories.GlobalInformationRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import com.nhaarman.mockito_kotlin.whenever
import com.sunmap.sunelectric.map.models.GlobalInformation
import com.sunmap.sunelectric.map.services.GlobalInformationService
import com.sunmap.sunelectric.map.utils.GlobalInformationBuilder
import com.sunmap.sunelectric.map.utils.GlobalInformationDTOBuilder
import org.assertj.core.api.Assertions.assertThat
import org.mockito.InjectMocks
import java.time.LocalDateTime

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class GlobalInformationServiceTest {
    @Mock
    lateinit var globalInformationRepository: GlobalInformationRepository

    @InjectMocks
    lateinit var globalInformationService: GlobalInformationService

    @Test
    fun getAllInformation_getsAllInformation() {
        val expectedGlobalInformation = GlobalInformation(1, 1000, 2000, 20)
        whenever(globalInformationRepository.findTopByOrderByIdDesc()).thenReturn(expectedGlobalInformation)

        val globalInformation = globalInformationService.getAllInformation()

        verify(globalInformationRepository).findTopByOrderByIdDesc()
        assertThat(globalInformation.totalConsumption).isEqualTo(expectedGlobalInformation.totalConsumption)
        assertThat(globalInformation.totalGeneration).isEqualTo(expectedGlobalInformation.totalGeneration)
        assertThat(globalInformation.carbonCredit).isEqualTo(expectedGlobalInformation.carbonCredit)
    }


    @Test
    fun saveNewInformation_savesNewInformation() {
        val currentDateTime = LocalDateTime.now()
        val expectedGlobalInformation = GlobalInformationBuilder(date = currentDateTime).default()
        val globalInformationDTO = GlobalInformationDTOBuilder(date = currentDateTime).default()

        whenever(globalInformationRepository.save(GlobalInformation.fromDto(globalInformationDTO))).thenReturn(expectedGlobalInformation)

        val globalInformation = globalInformationService.saveNewInformation(globalInformationDTO)

        verify(globalInformationRepository).save(GlobalInformation.fromDto(globalInformationDTO))
        assertThat(globalInformation.totalConsumption).isEqualTo(expectedGlobalInformation.totalConsumption)
        assertThat(globalInformation.totalGeneration).isEqualTo(expectedGlobalInformation.totalGeneration)
        assertThat(globalInformation.carbonCredit).isEqualTo(expectedGlobalInformation.carbonCredit)
    }

}