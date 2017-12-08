package com.sunmap.sunelectric.map.repositories

import com.sunmap.sunelectric.map.models.ConsumerAccount
import org.springframework.data.jpa.repository.JpaRepository

interface ConsumerAccountRepository : JpaRepository<ConsumerAccount, Long> {
    fun findByAddress(address: String?): ConsumerAccount
    fun findByMssl(mssl: String?): ConsumerAccount
    override fun findAll(): List<ConsumerAccount>
}