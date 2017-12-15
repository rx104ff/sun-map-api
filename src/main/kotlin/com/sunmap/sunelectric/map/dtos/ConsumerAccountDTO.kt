package com.sunmap.sunelectric.map.dtos

data class ConsumerAccountDTO(
        val address: String? = null,
        val solarPlan: String? = null,
        val mssl:String? = null,
        val generatorAddress: List<String>? = null,
        var mapCoordinates: List<Double>? = null
) {
    fun withCoordiantes (coordinates: List<Double>?) {
        this.mapCoordinates = coordinates
    }
}