package com.sunmap.sunelectric.map.repositories

import com.sunmap.sunelectric.map.models.GlobalInformation
import org.springframework.data.jpa.repository.JpaRepository

interface CompanyDetailRepository : JpaRepository<GlobalInformation, Long>