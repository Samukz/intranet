package com.web.tornese.SpringWeb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class Enderecos {


    @GetMapping("/enderecos")
    public String index() {

        return "enderecos/enderecos";
    }
    
}
