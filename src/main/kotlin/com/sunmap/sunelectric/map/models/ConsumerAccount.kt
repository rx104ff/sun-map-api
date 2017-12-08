package com.sunmap.sunelectric.map.models

import com.sunmap.sunelectric.map.dtos.ConsumerAccountDTO
import com.sunmap.sunelectric.map.enums.SolarPlan
import javax.persistence.*

@Entity
data class ConsumerAccount(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null,

        @Column(nullable = false)
        var address: String? = null,

        @Column(nullable = false)
        var solarPlan: SolarPlan? = null,

        @Column(nullable = false)
        var mssl: String? = null
) {
    fun toDto(): ConsumerAccountDTO {
        return ConsumerAccountDTO(
                address = address,
                solarPlan = solarPlan.toString(),
                mssl = mssl
        )
    }

    companion object {
        fun fromDto(dto: ConsumerAccountDTO): ConsumerAccount {
            return ConsumerAccount(
                    address = dto.address,
                    solarPlan =  solarPlanMatcher(dto.solarPlan!!),
                    mssl = dto.mssl
            )
        }

        private fun solarPlanMatcher(solarPlanString: String): SolarPlan{
            val solarPlan: SolarPlan? = when{
                solarPlanString == SolarPlan.SolarPEAK.toString() -> SolarPlan.SolarPEAK
                solarPlanString == SolarPlan.SolarLITE.toString() -> SolarPlan.SolarLITE
                solarPlanString == SolarPlan.SolarFLEX.toString() -> SolarPlan.SolarFLEX
                else -> null
            }
            return solarPlan!!
        }
    }

}