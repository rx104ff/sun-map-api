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

        @ManyToMany(cascade = arrayOf(CascadeType.ALL))
        @JoinTable(name = "consumer_matchup_id",
                joinColumns = arrayOf(JoinColumn(name = "consumer_matchup_id", referencedColumnName = "id")),
                inverseJoinColumns = arrayOf(JoinColumn(name = "generation_matchup-id", referencedColumnName = "id")))
        var consumerAccounts: List<ConsumerAccount> = mutableListOf()
) {
    fun toDto(): GeneratorAccountDTO {
        return GeneratorAccountDTO(
                address = address,
                consumerAddress = consumerAccounts.map{ it.address!! }
        )
    }

    companion object {
        fun fromDto(dto: GeneratorAccountDTO): GeneratorAccount {
            return GeneratorAccount(
                    address = dto.address
            )
        }
    }
}