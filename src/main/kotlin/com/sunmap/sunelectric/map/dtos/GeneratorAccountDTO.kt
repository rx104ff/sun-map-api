package com.sunmap.sunelectric.map.dtos

data class GeneratorAccountDTO(
        val address: String? = null,
        val consumerAddress: List<String>? = null,
        var mapCoordinates: List<Double>? = null
) {
    fun withCoordiantes (coordinates: List<Double>?) {
        this.mapCoordinates = coordinates
    }
}