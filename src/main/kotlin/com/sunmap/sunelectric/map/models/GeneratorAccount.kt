package com.sunmap.sunelectric.map.models

import com.sunmap.sunelectric.map.dtos.GeneratorAccountDTO
import javax.persistence.*

@Entity
data class GeneratorAccount(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column(nullable = false)
        var address: String? = null
) {
    fun toDto(): GeneratorAccountDTO {
        return GeneratorAccountDTO(
                address = address
        )
    }
}