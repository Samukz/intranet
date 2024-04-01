package com.web.tornese.SpringWeb.controllers;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.web.tornese.SpringWeb.Servico.CookieService;
import com.web.tornese.SpringWeb.Servico.ItemService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

   @Autowired
    private ItemService itemService;

  @GetMapping("/")
  public String index(Model model, HttpServletRequest request) throws UnsupportedEncodingException{
    model.addAttribute("nome", CookieService.getCookie(request, "nomeUsuario"));

    Double totalValor = itemService.getTotalValor();
    model.addAttribute("totalValor", totalValor);

    Double maiorValor = itemService.getMaiorValor();
    model.addAttribute("maiorValor", maiorValor);

    Long totalItems = itemService.getTotalItems();
    model.addAttribute("totalItems", totalItems);

    Long totalItemsObsoletos = itemService.totalItemsObsoletos();
    model.addAttribute("totalItemsObsoletos", totalItemsObsoletos);
    
    List<Object[]> sumValorByData = itemService.getSumValorByData();
    model.addAttribute("sumValorByData", sumValorByData);

    List<Object[]> sumValorPorLocal = itemService.getsumValorPorLocal();
    model.addAttribute("sumValorPorLocal", sumValorPorLocal);
    
    return "home/index";
  }
}
