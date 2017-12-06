package com.sunmap.sunelectric.map.ControllerTest

import com.sunmap.sunelectric.map.controllers.GlobalInformationController
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup
import org.mockito.MockitoAnnotations
import org.mockito.InjectMocks
import org.springframework.web.context.WebApplicationContext
import org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class GlobalInformationControllerTest  {

    @Autowired
    private val context: WebApplicationContext? = null

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun getGlobalInformation() {

        mockMvc!!
                .perform(MockMvcRequestBuilders.post("/global")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
    }
}