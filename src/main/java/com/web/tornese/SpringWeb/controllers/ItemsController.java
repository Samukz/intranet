package com.web.tornese.SpringWeb.controllers;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Image;
import com.itextpdf.io.image.ImageDataFactory;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.StorageOptions;
import com.web.tornese.SpringWeb.Servico.CookieService;
import com.web.tornese.SpringWeb.Servico.ExcelGenerator;
import com.web.tornese.SpringWeb.models.Armazenagem;
import com.web.tornese.SpringWeb.models.Catalogo;
import com.web.tornese.SpringWeb.models.Grupo;
import com.web.tornese.SpringWeb.models.Item_Unit;
import com.web.tornese.SpringWeb.models.Unidades;
import com.web.tornese.SpringWeb.repositorio.ArmazenagemRepository;
import com.web.tornese.SpringWeb.repositorio.CatalogoRepo;
import com.web.tornese.SpringWeb.repositorio.GruposRepository;
import com.web.tornese.SpringWeb.repositorio.UnidadesRepository;
import com.web.tornese.SpringWeb.repositorio.ItemUnitRepository;

import org.springframework.util.StringUtils;

@Controller
public class ItemsController {

  @Autowired
  private CatalogoRepo repo;

  @Autowired
  private ItemUnitRepository itemUnitRepository;

  @GetMapping("/items")
  public String index(Model model){
    List<Catalogo> items = (List<Catalogo>)repo.findAll();
    model.addAttribute("items", items);
    return "items/index";
  }

  @GetMapping("/items/relatorio")
  public String index2(Model model) {
    List<Catalogo> items = (List<Catalogo>) repo.findAll();
    model.addAttribute("items", items);

    List<Unidades> unidades = unidadesRepository.findAll(); // Busca todas as unidades
    model.addAttribute("unidades", unidades); // Adiciona a lista de unidades ao modelo

    List<Armazenagem> armazenagens = ArmazenagemRepository.findAll(); // Busca todas as unidades
    model.addAttribute("Armazenagem", armazenagens);

    List<Grupo> grupos = GruposRepository.findAll(); // Busca todas as unidades
    model.addAttribute("Grupo", grupos);
    
    return "items/relatorio";
  }

  @Autowired
  private ArmazenagemRepository ArmazenagemRepository;
  @Autowired
  private GruposRepository GruposRepository;

        @GetMapping("/items/novo")
        public String novo(Model model, HttpServletRequest request)throws UnsupportedEncodingException{

           model.addAttribute("nome", CookieService.getCookie(request, "nomeUsuario"));

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
        public String criar( Catalogo item, @RequestParam("imagem") MultipartFile imagem,
            @RequestParam("unidadeIds") List<Long> unidadeIds) throws IOException {
          // Processamento da imagem
          if (!imagem.isEmpty()) {
            String nomeDoArquivoImagem = StringUtils.cleanPath(Objects.requireNonNull(imagem.getOriginalFilename()));
            String extensaoImagem = nomeDoArquivoImagem.substring(nomeDoArquivoImagem.lastIndexOf('.'));
            String nomeDoArquivoUnicoImagem = UUID.randomUUID().toString() + extensaoImagem;

            BlobId blobIdImagem = BlobId.of("imagens_doit", "img-uploads-catalogo/" + nomeDoArquivoUnicoImagem);
            BlobInfo blobInfoImagem = BlobInfo.newBuilder(blobIdImagem).build();

            Blob blobImagem = StorageOptions.getDefaultInstance().getService().create(blobInfoImagem,
                imagem.getBytes());

            String imageUrl = "https://storage.googleapis.com/" + blobImagem.getBucket() + "/" + blobImagem.getName();
            item.setCaminhoDaImagem(imageUrl);
          } else {
            item.setCaminhoDaImagem(
                "https://storage.googleapis.com/imagens_doit/img-uploads-catalogo/produto-sem-imagem.jpg");
          }

          // Salvar o item no banco de dados
          Catalogo savedItem = repo.save(item);

          // Salvar as unidades relacionadas no banco de dados
          for (Long unidadeId : unidadeIds) {
            Item_Unit itemUnit = new Item_Unit();
            itemUnit.setItem_id((long) savedItem.getId()); // Presume-se que Catalogo tem um método getId()
            itemUnit.setUnidade_id(unidadeId);
            itemUnitRepository.save(itemUnit);
          }

          return "redirect:/items";
        }



        @GetMapping("/items/{id}")
        public String busca(@PathVariable int id, Model model) {
          Optional<Catalogo> item = repo.findById(id);
          List<Grupo> grupos = GruposRepository.findAll(); // Supondo que você tenha um repositório chamado grupoRepo
          List<Unidades> unidades = unidadesRepository.findAll();
          List<Armazenagem> armazenagemList = ArmazenagemRepository.findAll();

          try {
            model.addAttribute("item", item.get());
            model.addAttribute("Grupo", grupos); // Adicionando a lista de grupos ao modelo
            model.addAttribute("unidades", unidades);
            model.addAttribute("Armazenagem", armazenagemList);
          } catch (Exception err) {
            return "redirect:/items";
          }

          return "items/editar";
        }

        @PostMapping("/items/{id}/atualizar")
        public String atualizar(@PathVariable int id, Catalogo itemAtualizado,
            @RequestParam("imagem") MultipartFile imagem,
            @RequestParam("notafiscal") MultipartFile notafiscal) throws IOException {
          Optional<Catalogo> itemExistenteOp = repo.findById(id);
          if (!itemExistenteOp.isPresent()) {
            return "redirect:/items";
          }

          Catalogo itemExistente = itemExistenteOp.get();

          if (!imagem.isEmpty()) {
            String nomeDoArquivoImagem = StringUtils.cleanPath(Objects.requireNonNull(imagem.getOriginalFilename()));
            String extensaoImagem = nomeDoArquivoImagem.substring(nomeDoArquivoImagem.lastIndexOf('.'));
            String nomeDoArquivoUnicoImagem = UUID.randomUUID().toString() + extensaoImagem;
            BlobId blobIdImagem = BlobId.of("imagens_doit", "img-uploads/" + nomeDoArquivoUnicoImagem);
            BlobInfo blobInfoImagem = BlobInfo.newBuilder(blobIdImagem).build();
            com.google.cloud.storage.Storage storage = StorageOptions.getDefaultInstance().getService();
            Blob blobImagem = storage.create(blobInfoImagem, imagem.getBytes());
            String imageUrl = "https://storage.googleapis.com/" + blobImagem.getBucket() + "/" + blobImagem.getName();
            itemExistente.setCaminhoDaImagem(imageUrl);
          } // Se não enviou imagem, mantém a existente


          repo.save(itemExistente); // Atualiza o item existente com novas informações

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
                Optional<Catalogo> item = repo.findById(id);
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
