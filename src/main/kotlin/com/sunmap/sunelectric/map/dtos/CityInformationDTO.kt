package com.sunmap.sunelectric.map.dtos

data class CityInformationDTO(
        val name: String? = null,
        var mapCoordinates: List<Double>? = null
) {
    fun withCoordiantes (coordinates: List<Double>?) {
        this.mapCoordinates = coordinates
    }
}