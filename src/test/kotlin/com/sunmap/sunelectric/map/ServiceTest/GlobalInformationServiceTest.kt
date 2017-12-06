package com.sunmap.sunelectric.map.ServiceTest

import com.nhaarman.mockito_kotlin.verify
import com.sunmap.sunelectric.map.repositories.GlobalInformationRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import com.nhaarman.mockito_kotlin.whenever
import com.sunmap.sunelectric.map.models.GlobalInformation
import com.sunmap.sunelectric.map.services.GlobalInformationService
import org.assertj.core.api.Assertions.assertThat
import org.mockito.InjectMocks

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class GlobalInformationServiceTest {
    @Autowired
    lateinit var mockMvc: MockMvc

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

}