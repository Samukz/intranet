package com.web.tornese.SpringWeb.controllers;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import com.web.tornese.SpringWeb.Servico.CookieService;
import com.web.tornese.SpringWeb.Servico.UnidadeService;
import com.web.tornese.SpringWeb.models.Fornecedores;
import com.web.tornese.SpringWeb.models.Grupo;
import com.web.tornese.SpringWeb.models.Medidas;
import com.web.tornese.SpringWeb.models.Unidades;
import com.web.tornese.SpringWeb.repositorio.FornecedoresRepo;
import com.web.tornese.SpringWeb.repositorio.GruposRepository;
import com.web.tornese.SpringWeb.repositorio.UnidadesMedidaRepository;
import com.web.tornese.SpringWeb.repositorio.UnidadesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GrupoControoller {
  

  @Autowired
  private UnidadesMedidaRepository unidadesMedidaRepository;

  @Autowired
  private FornecedoresRepo forn;

  @Autowired
  private UnidadesRepository unidadesrepository;

  @Autowired
  private GruposRepository gruposRepository;

  @GetMapping("/")
  public String index(Model model, HttpServletRequest request) throws UnsupportedEncodingException{
    model.addAttribute("nome", CookieService.getCookie(request, "nomeUsuario"));

    List<Medidas> medidas = unidadesMedidaRepository.findAll();
    model.addAttribute("Medidas", medidas);

    List<Grupo> grupoList = gruposRepository.findAll();

    // Filtrar duplicatas com base no nome do grupo
    List<Grupo> grupoListSemDuplicados = grupoList.stream()
        .collect(Collectors.toMap(Grupo::getGrupo, grupo -> grupo, (existing, replacement) -> existing))
        .values()
        .stream()
        .collect(Collectors.toList());

    model.addAttribute("Grupo", grupoListSemDuplicados);

    model.addAttribute("unidades", unidadeService.listarUnidades());


    ////PRA BAIXO = FORMS

    List<Fornecedores> fornecedores = forn.findAll();
    model.addAttribute("Fornecedores", fornecedores);



    
    return "home/index";
  }


  @GetMapping("/subgrupos")
  public ResponseEntity<List<String>> getSubgrupos(@RequestParam String grupo) {
    List<Grupo> subgrupos = gruposRepository.findByGrupo(grupo);
    List<String> nomesSubgrupos = subgrupos.stream()
        .map(Grupo::getSubgrupo)
        .distinct()
        .collect(Collectors.toList());
    return ResponseEntity.ok(nomesSubgrupos);
  }

  @Autowired
  private UnidadeService unidadeService;

  @GetMapping("/centros-de-custo")
  public @ResponseBody List<Unidades> getCentrosDeCusto() {
      return unidadesrepository.findAll();
  }

  }
