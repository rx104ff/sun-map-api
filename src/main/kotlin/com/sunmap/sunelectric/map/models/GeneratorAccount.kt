package com.sunmap.sunelectric.map.models

import com.sunmap.sunelectric.map.dtos.GeneratorAccountDTO
import javax.persistence.*

@Entity
@Table(name = "generator")
data class GeneratorAccount(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column(nullable = false)
        var address: String? = null,

        @ElementCollection
        @Column(nullable = false)
        val mapCoordinates: List<Double>? = null,

        @ManyToMany(cascade = arrayOf(CascadeType.ALL))
        @JoinTable(name = "consumer_accounts",
                joinColumns = arrayOf(JoinColumn(name = "generator_accounts", referencedColumnName = "id")),
                inverseJoinColumns = arrayOf(JoinColumn(name = "consumer_accounts", referencedColumnName = "id")))
        var consumerAccounts: List<ConsumerAccount> = mutableListOf()
) {
    fun toDto(): GeneratorAccountDTO {
        return GeneratorAccountDTO(
                address = address,
                mapCoordinates = mapCoordinates,
                consumerAddress = consumerAccounts.map{ it.address!! }
        )
    }

    companion object {
        fun fromDto(dto: GeneratorAccountDTO): GeneratorAccount {
            return GeneratorAccount(
                    address = dto.address,
                    mapCoordinates = dto.mapCoordinates
            )
        }
    }
}