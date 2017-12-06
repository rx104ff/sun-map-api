package com.sunmap.sunelectric.map.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/global")
class GlobalInformationController {
    @GetMapping("")
    fun getCurrentConsumerAccount(): String {
        return "HHHHHHHH"
    }
}