package com.sunmap.sunelectric.map.repositories

import com.sunmap.sunelectric.map.models.GeneratorAccount
import org.springframework.data.jpa.repository.JpaRepository

interface GeneratorAccountRepository: JpaRepository<GeneratorAccount, Long> {
    fun findByAddress(address: String?): GeneratorAccount
    override fun findAll(): List<GeneratorAccount>
    fun removeByAddress(address: String?): Long
}