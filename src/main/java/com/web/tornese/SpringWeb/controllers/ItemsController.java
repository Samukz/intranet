package com.web.tornese.SpringWeb.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.PostConstruct;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.StorageOptions;
import com.web.tornese.SpringWeb.models.Armazenagem;
import com.web.tornese.SpringWeb.models.Grupo;
import com.web.tornese.SpringWeb.models.Item;
import com.web.tornese.SpringWeb.models.Unidades;
import com.web.tornese.SpringWeb.repositorio.ArmazenagemRepository;
import com.web.tornese.SpringWeb.repositorio.GruposRepository;
import com.web.tornese.SpringWeb.repositorio.ItemsRepo;
import com.web.tornese.SpringWeb.repositorio.UnidadesRepository;

import org.springframework.util.StringUtils;

@Controller
public class ItemsController {

  @Autowired
  private ItemsRepo repo;

  @GetMapping("/items")
  public String index(Model model){
    List<Item> items = (List<Item>)repo.findAll();
    model.addAttribute("items", items);
    return "items/index";
  }

  @Autowired
  private ArmazenagemRepository ArmazenagemRepository;
  @Autowired
  private GruposRepository GruposRepository;

        @GetMapping("/items/novo")
        public String novo(Model model){

          List<Unidades> unidades = unidadesRepository.findAll(); // Busca todas as unidades
          model.addAttribute("unidades", unidades); // Adiciona a lista de unidades ao modelo

          List<Armazenagem> armazenagens = ArmazenagemRepository.findAll(); // Busca todas as unidades
          model.addAttribute("Armazenagem", armazenagens);
          
          List<Grupo> grupos = GruposRepository.findAll(); // Busca todas as unidades
          model.addAttribute("Grupo", grupos);

          return "items/novo";
        }

        // @PostMapping("/items/criar")
        // public String criar(Item item){
        //   repo.save(item);
        //   return "redirect:/items";
        // }

        @Value("${app.image.directory}")
        private String imageDirectory;

                @PostConstruct
        public void init() {
            Path diretorioDeUpload = Paths.get(imageDirectory);
            try {
                Files.createDirectories(diretorioDeUpload);
            } catch (IOException e) {
                throw new RuntimeException("Não foi possível criar o diretório de imagens", e);
            }
        }


        @PostMapping("/items/criar")
        public String criar(Item item, @RequestParam("imagem") MultipartFile imagem,
            @RequestParam("notafiscal") MultipartFile notafiscal) throws IOException {
          if (!imagem.isEmpty()) {
            String nomeDoArquivoImagem = StringUtils.cleanPath(Objects.requireNonNull(imagem.getOriginalFilename()));
            String extensaoImagem = nomeDoArquivoImagem.substring(nomeDoArquivoImagem.lastIndexOf('.'));
            String nomeDoArquivoUnicoImagem = UUID.randomUUID().toString() + extensaoImagem;

            BlobId blobIdImagem = BlobId.of("imagens_doit", "img-uploads/" + nomeDoArquivoUnicoImagem);
            BlobInfo blobInfoImagem = BlobInfo.newBuilder(blobIdImagem).build();

            com.google.cloud.storage.Storage storage = StorageOptions.getDefaultInstance().getService();
            Blob blobImagem = storage.create(blobInfoImagem, imagem.getBytes());

            String imageUrl = "https://storage.googleapis.com/" + blobImagem.getBucket() + "/" + blobImagem.getName();
            item.setCaminhoDaImagem(imageUrl);
          }

          if (!notafiscal.isEmpty()) {
            String nomeDoArquivoNota = StringUtils.cleanPath(Objects.requireNonNull(notafiscal.getOriginalFilename()));
            String extensaoNota = nomeDoArquivoNota.substring(nomeDoArquivoNota.lastIndexOf('.'));
            String nomeDoArquivoUnicoNota = UUID.randomUUID().toString() + extensaoNota;

            BlobId blobIdNota = BlobId.of("imagens_doit", "nota-fiscal-uploads/" + nomeDoArquivoUnicoNota); // Crie uma
                                                                                                            // nova
                                                                                                            // pasta
                                                                                                            // para
                                                                                                            // notas
                                                                                                            // fiscais
            BlobInfo blobInfoNota = BlobInfo.newBuilder(blobIdNota).build();

            // Cria a conexão com o Storage para o arquivo de nota fiscal
            com.google.cloud.storage.Storage storage = StorageOptions.getDefaultInstance().getService();
            Blob blobNota = storage.create(blobInfoNota, notafiscal.getBytes());

            String notaFiscalUrl = "https://storage.googleapis.com/" + blobNota.getBucket() + "/" + blobNota.getName();
            item.setCaminhoDaNotaFiscal(notaFiscalUrl); // Supondo que você tenha um campo para isso em seu objeto item
          }


    repo.save(item);
    return "redirect:/items";
}



        @GetMapping("/items/{id}")
        public String busca(@PathVariable int id, Model model){
          Optional<Item> item = repo.findById(id);
          try{
            model.addAttribute("item", item.get());
          }
          catch(Exception err){ return "redirect:/items"; }

          return "items/editar";
        }

        @PostMapping("/items/{id}/atualizar")
        public String atualizar(@PathVariable int id, Item item){
          // if(!repo.exist(id)){
          if(!repo.existsById(id)){
            return "redirect:/Items";
          }

          repo.save(item);

          return "redirect:/items";
        }


      @GetMapping("/items/{id}/excluir")
      public String excluir(@PathVariable int id){
        repo.deleteById(id);
        return "redirect:/items";
      }

      @GetMapping("/items/visualizar/{id}")
      public String buscar(@PathVariable int id, Model model) {
        List<Unidades> unidades = unidadesRepository.findAll(); // Busca todas as unidades
        Optional<Item> item = repo.findById(id);
        try {
          model.addAttribute("item", item.get());

        } catch (Exception err) {
          return "redirect:/items";
        }

        return "items/visualizar";
      }

      @Autowired
      private UnidadesRepository unidadesRepository; // Adicione isso ao seu controller

      @GetMapping("/unidades/selecionar")
      public String listarUnidades(Model model) {
          List<Unidades> unidades = unidadesRepository.findAll(); // Busca todas as unidades
          model.addAttribute("unidades", unidades); // Adiciona a lista de unidades ao modelo
          return "local"; // Substitua pelo nome do seu arquivo HTML
      }


}
