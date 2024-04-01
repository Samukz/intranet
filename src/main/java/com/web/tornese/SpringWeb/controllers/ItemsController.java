package com.web.tornese.SpringWeb.controllers;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

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
import com.web.tornese.SpringWeb.Servico.ItemService;
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

  @GetMapping("/items/relatorio")
  public String index2(Model model) {
    List<Item> items = (List<Item>) repo.findAll();
    model.addAttribute("items", items);
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
        public String busca(@PathVariable int id, Model model) {
          Optional<Item> item = repo.findById(id);
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
        public String atualizar(@PathVariable int id, Item itemAtualizado,
            @RequestParam("imagem") MultipartFile imagem,
            @RequestParam("notafiscal") MultipartFile notafiscal) throws IOException {
          Optional<Item> itemExistenteOp = repo.findById(id);
          if (!itemExistenteOp.isPresent()) {
            return "redirect:/items";
          }

          Item itemExistente = itemExistenteOp.get();

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

          if (!notafiscal.isEmpty()) {
            String nomeDoArquivoNota = StringUtils.cleanPath(Objects.requireNonNull(notafiscal.getOriginalFilename()));
            String extensaoNota = nomeDoArquivoNota.substring(nomeDoArquivoNota.lastIndexOf('.'));
            String nomeDoArquivoUnicoNota = UUID.randomUUID().toString() + extensaoNota;
            BlobId blobIdNota = BlobId.of("imagens_doit", "nota-fiscal-uploads/" + nomeDoArquivoUnicoNota);
            BlobInfo blobInfoNota = BlobInfo.newBuilder(blobIdNota).build();
            com.google.cloud.storage.Storage storage = StorageOptions.getDefaultInstance().getService();
            Blob blobNota = storage.create(blobInfoNota, notafiscal.getBytes());
            String notaFiscalUrl = "https://storage.googleapis.com/" + blobNota.getBucket() + "/" + blobNota.getName();
            itemExistente.setCaminhoDaNotaFiscal(notaFiscalUrl);
          } // Se não enviou nota fiscal, mantém a existente

          // Aqui você pode atualizar os demais campos do itemExistente com os valores de
          // itemAtualizado
          // Este passo depende de como você deseja lidar com os outros campos no processo
          // de atualização

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

      @GetMapping("/verificarExistencia")
      public ResponseEntity<Boolean> verificarExistencia(@RequestParam("numeroPatrimonio") String numeroPatrimonio) {
        System.out.println("Solicitação para verificar existência do patrimônio: " + numeroPatrimonio);
        int count = repo.existsByNumeroPatrimonio(numeroPatrimonio);
        boolean existe = count > 0;
        System.out.println("Existe: " + existe);
        return ResponseEntity.ok(existe);
      }

      @RestController
      public class ExportController {

          private final ItemService itemService; // Supondo que você tenha um serviço para buscar os itens

          public ExportController(ItemService itemService) {
              this.itemService = itemService;
          }

          @GetMapping("/exportar")
          public StreamingResponseBody exportarExcel(HttpServletResponse response,
                                                      @RequestParam(required = false) String nome,
                                                      @RequestParam(required = false) String local,
                                                      @RequestParam(required = false) String categoria,
                                                      @RequestParam(required = false) String estado) {
              response.setContentType("application/vnd.ms-excel");
              response.setHeader("Content-Disposition", "attachment; filename=\"export-items.xlsx\"");

              return outputStream -> {
                  List<Item> itemsFiltrados = itemService.buscarItensFiltrados(nome, local, categoria, estado);
                  ExcelGenerator.generateExcel(itemsFiltrados, outputStream);
              };
          }
      }



}
