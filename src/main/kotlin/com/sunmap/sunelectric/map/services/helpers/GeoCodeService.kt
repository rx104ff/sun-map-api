package com.sunmap.sunelectric.map.services.helpers

import org.json.JSONArray
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URI
import java.net.URL

@Service
class GeoCodeService {
    @Throws(Exception::class)
    fun getCoordinates(address: String): List<Double> {
        val accessToken: String = "pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4M29iazA2Z2gycXA4N2pmbDZmangifQ.-g_vE53SD2WrJ6tFX7QHmA"

        val uri = URI(
                "https",
                "api.mapbox.com",
                "/v4/geocode/mapbox.places/$address.json",
                null)
        val url = uri.toURL()
        val obj = URL("https://${uri.host}${url.path}?access_token=$accessToken")
        val con = obj.openConnection() as HttpURLConnection

        con.requestMethod = "GET"

        val responseCode = con.responseCode

        val locationJson = BufferedReader(
                InputStreamReader(con.inputStream))

        val json: JSONArray = JSONArray(locationJson.readLines().toString())
        val cooridnateseArray = json.optJSONObject(0)
                .getJSONArray("features")
                .optJSONObject(0)
                .getJSONObject("geometry")
                .getJSONArray("coordinates")
        return listOf(cooridnateseArray.getDouble(0), cooridnateseArray.getDouble(1))
    }
}