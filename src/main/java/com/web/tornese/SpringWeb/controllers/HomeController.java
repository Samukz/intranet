package com.web.tornese.SpringWeb.controllers;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.web.tornese.SpringWeb.Servico.CookieService;
import com.web.tornese.SpringWeb.models.Grupo;
import com.web.tornese.SpringWeb.models.Medidas;
import com.web.tornese.SpringWeb.models.Unidades;
import com.web.tornese.SpringWeb.repositorio.GruposRepository;
import com.web.tornese.SpringWeb.repositorio.UnidadesMedidaRepository;
import com.web.tornese.SpringWeb.repositorio.UnidadesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

  @Autowired
  private UnidadesMedidaRepository unidadesMedidaRepository;

  @Autowired
  private GruposRepository gruposRepository;

  @GetMapping("/2")
  public String index(Model model, HttpServletRequest request) throws UnsupportedEncodingException{
    model.addAttribute("nome", CookieService.getCookie(request, "nomeUsuario"));

    List<Medidas> medidas = unidadesMedidaRepository.findAll();
    model.addAttribute("Medidas", medidas);

    List<Grupo> grupo = gruposRepository.findAll();
    model.addAttribute("Grupo", grupo);

    
    return "home/index";
  }



    
  }
