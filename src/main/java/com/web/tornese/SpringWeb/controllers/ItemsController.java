package com.web.tornese.SpringWeb.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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

import com.google.api.services.storage.Storage;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.StorageOptions;
import com.web.tornese.SpringWeb.models.Item;
import com.web.tornese.SpringWeb.repositorio.ItemsRepo;


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

  

        @GetMapping("/items/novo")
        public String novo(){
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
public String criar(Item item, @RequestParam("imagem") MultipartFile imagem) throws IOException {
    if (!imagem.isEmpty()) {
        // Gera um nome único para o arquivo baseado em um identificador, como um UUID
        String nomeDoArquivo = StringUtils.cleanPath(Objects.requireNonNull(imagem.getOriginalFilename()));
        String extensao = nomeDoArquivo.substring(nomeDoArquivo.lastIndexOf('.'));
        String nomeDoArquivoUnico = UUID.randomUUID().toString() + extensao;

        // Prepara o arquivo para upload
        BlobId blobId = BlobId.of("imagens_doit", "img-uploads/" + nomeDoArquivoUnico); // Substitua "seu-bucket" pelo nome do seu bucket
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
        
        // Cria a conexão com o Storage
        com.google.cloud.storage.Storage storage = StorageOptions.getDefaultInstance().getService();
        // Realiza o upload
        Blob blob = storage.create(blobInfo, imagem.getBytes());

        // Armazena a URL pública da imagem
        String imageUrl = "https://storage.googleapis.com/" + blob.getBucket() + "/" + blob.getName(); // URL direta para o arquivo
        item.setCaminhoDaImagem(imageUrl); // Salva a URL da imagem no objeto item
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

          return "/items/editar";
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
        Optional<Item> item = repo.findById(id);
        try {
          model.addAttribute("item", item.get());
        } catch (Exception err) {
          return "redirect:/items";
        }

        return "/items/visualizar";
      }

}
