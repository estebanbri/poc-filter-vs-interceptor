package com.example.pocfiltervsinterceptor.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/saludador")
public class Saludador {

    @GetMapping
    public String decirHola(){
        return "Hola";
    }

}
