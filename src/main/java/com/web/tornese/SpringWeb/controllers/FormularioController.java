package com.web.tornese.SpringWeb.controllers;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.io.IOException;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Paragraph;
import com.web.tornese.SpringWeb.Servico.CookieService;
import com.web.tornese.SpringWeb.Servico.PdfGenerationService;
import com.web.tornese.SpringWeb.Servico.StorageService;
import com.web.tornese.SpringWeb.models.Administrador;
import com.web.tornese.SpringWeb.models.Catalogo;
import com.web.tornese.SpringWeb.models.Formulario;
import com.web.tornese.SpringWeb.models.Fornecedores;
import com.web.tornese.SpringWeb.models.NotaFiscal;
import com.web.tornese.SpringWeb.models.Unidades;
import com.web.tornese.SpringWeb.repositorio.AdministradoresRepo;
import com.web.tornese.SpringWeb.repositorio.FormularioRepo;
import com.web.tornese.SpringWeb.repositorio.FornecedoresRepo;
import com.web.tornese.SpringWeb.repositorio.NotafiscalRepo;
import com.web.tornese.SpringWeb.repositorio.UnidadesRepository;



@Controller
public class FormularioController {

    @Autowired
    private FormularioRepo formularioRepo;

    @Autowired
    private FornecedoresRepo fornecedoresRepo;

    @Autowired
    private NotafiscalRepo notafiscalRepo;

    @Autowired
    private PdfGenerationService pdfGenerationService;

    @Autowired
    private StorageService storageService;

        @Autowired
    private AdministradoresRepo administradoresRepo;

    @Autowired
    private UnidadesRepository unidadesRepository;

    @PostMapping("/formulario/cadastrar")
    public String criar(@ModelAttribute Formulario formulario, @RequestParam List<String> centrodecusto, @RequestParam List<String> numeroNotaFiscal, @RequestParam List<String> numeroVolumes) {
        // Manipulação da propriedade veiculo, assumindo que vem como "veiculo1,veiculo2" etc.
        String veiculo = formulario.getVeiculo();
        if (veiculo != null && veiculo.contains(",")) {
            veiculo = veiculo.split(",")[0];
            formulario.setVeiculo(veiculo);
        }

        String entregarealizada = formulario.getEntregarealizada();
        if (entregarealizada != null) {
            entregarealizada = entregarealizada.split(",")[0];
            formulario.setEntregarealizada(entregarealizada); 
        }


                ///CAMPO .5 (termolabeis check)
                String temolabel = formulario.getTemolabel();
                if (temolabel != null) {
                    temolabel = temolabel.split(",")[0];
                    formulario.setTemolabel(temolabel); 
                 }

                 ///CAMPO .5 (material parcial)
                String materialparcial = formulario.getMaterialparcial();
                if (materialparcial != null) {
                    materialparcial = materialparcial.split(",")[0];
                    formulario.setMaterialparcial(materialparcial); 
                 }

                 ///CAMPO .5 (termolabeis sim,não)
                String termolabelsimnao = formulario.getTermolabelsimnao();
                if (termolabelsimnao != null) {
                    termolabelsimnao = termolabelsimnao.split(",")[0];
                    formulario.setTermolabelsimnao(termolabelsimnao); 
                 }


                ///CAMPO .5
                String materialentregue = formulario.getMaterialentregue();
                if (materialentregue != null) {
                    materialentregue = materialentregue.split(",")[0];
                    formulario.setMaterialentregue(materialentregue); 
                 }

                 ///CAMPO .5.1
                String embalagemdanificada = formulario.getEmbalagemdanificada();
                if (embalagemdanificada != null) {
                    embalagemdanificada = embalagemdanificada.split(",")[0];
                    formulario.setEmbalagemdanificada(embalagemdanificada); 
                 }

                ///CAMPO .5.1
                String embalagemdanificadainfo = formulario.getEmbalagemdanificadainfo();
                if (embalagemdanificadainfo != null) {
                    embalagemdanificadainfo = embalagemdanificadainfo.split(",")[0];
                    formulario.setEmbalagemdanificadainfo(embalagemdanificadainfo); 
                 }

                ///CAMPO .6
                String lotevalidade = formulario.getLotevalidade();
                if (lotevalidade != null) {
                    int lastIndex = lotevalidade.lastIndexOf(",");
                    if (lastIndex != -1) { // Verifica se uma vírgula foi encontrada
                        lotevalidade = lotevalidade.substring(0, lastIndex);
                    }
                    formulario.setLotevalidade(lotevalidade);
                }


                ///CAMPO 9.
                String agendamento = formulario.getAgendamento();
                if (agendamento != null) {
                    agendamento = agendamento.split(",")[0];
                    formulario.setAgendamento(agendamento); 
                 }

                ///CAMPO 10.
                String datahoracumprida = formulario.getDatahoracumprida();
                if (datahoracumprida != null) {
                    datahoracumprida = datahoracumprida.split(",")[0];
                    formulario.setDatahoracumprida(datahoracumprida);; 
                 }

                 ///CAMPO 11.
                 String organizadanf = formulario.getOrganizadanf();
                 if (organizadanf != null) {
                    organizadanf = organizadanf.split(",")[0];
                     formulario.setOrganizadanf(organizadanf);
                 }

                 ///CAMPO 12.
                 String cargaembalada = formulario.getCargaembalada();
                 if (cargaembalada != null) {
                    cargaembalada = cargaembalada.split(",")[0];
                     formulario.setCargaembalada(cargaembalada);
                 }

                 ///CAMPO 13.
                 String ajudante = formulario.getAjudante();
                 if (ajudante != null) {
                    ajudante = ajudante.split(",")[0];
                     formulario.setAjudante(ajudante);
                 }

                 ///CAMPO 14
                String entregaaceita = formulario.getEntregaaceita();
                if (entregaaceita != null) {
                    int lastIndex = entregaaceita.lastIndexOf(",");
                    if (lastIndex != -1) { // Verifica se uma vírgula foi encontrada
                        entregaaceita = entregaaceita.substring(0, lastIndex);
                    }
                    formulario.setEntregaaceita(entregaaceita);
                }


                 ///CAMPO 7 IMPRESSO OF.
                 String documentoimpressosof = formulario.getDocumentoimpressosof();
                 if (documentoimpressosof != null) {
                    documentoimpressosof = documentoimpressosof.split(",")[0];
                     formulario.setDocumentoimpressosof(documentoimpressosof);
                 }




        // Salvando o objeto formulário após a manipulação
        formulario = formularioRepo.save(formulario);

                    // Iterando sobre as notas fiscais e salvando cada uma
                    for (int i = 0; i < numeroNotaFiscal.size(); i++) {
                        NotaFiscal notafiscal = new NotaFiscal();
                        notafiscal.setFormulario(formulario); // Ajuste conforme a chave estrangeira apropriada
                        notafiscal.setNumeroNotaFiscal(numeroNotaFiscal.get(i));
                        notafiscal.setNumeroVolumes(numeroVolumes.get(i));
                        notafiscal.setCentroDeCusto(centrodecusto.get(i));
                        notafiscalRepo.save(notafiscal);
                    }


        return "redirect:/";
    }

    @GetMapping("/listar-formularios")
    public String index(Model model, HttpServletRequest request) throws UnsupportedEncodingException {
        model.addAttribute("nome", CookieService.getCookie(request, "nomeUsuario"));
        List<Formulario> items = formularioRepo.findAllWithFornecedorAndNotas();
        items.forEach(item -> {
            Set<NotaFiscal> uniqueNotas = new HashSet<>(item.getNotasFiscais());
            item.setNotasFiscais(new ArrayList<>(uniqueNotas));
        });
        model.addAttribute("items", items);

        List<Administrador> administradores = administradoresRepo.findAllAdministradores();
        model.addAttribute("administradores", administradores);

        List<Unidades> unidades = unidadesRepository.findAll();
        model.addAttribute("unidades", unidades);

        return "items/impressao";
    }

    




  @PostMapping("/formulario/generate-pdf")
    public ResponseEntity<byte[]> generatePDF(@RequestParam("html") String html) {
        System.out.println("HTML parameter received: " + html); // Adicionando log
        try {
            byte[] pdfBytes = pdfGenerationService.generatePdfFromHtml(html);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDispositionFormData("attachment", "output.pdf");
            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

                @PostMapping("/formulario/upload-pdf")
                public ResponseEntity<String> uploadPDFAndData(@RequestParam("pdf") MultipartFile pdf, @RequestParam Map<String, String> allParams) {
                    try {
                        byte[] content = pdf.getBytes();
                        System.out.println("Received file: " + pdf.getOriginalFilename() + " size: " + content.length);
                        storageService.uploadFile(content, "imagens_doit", pdf.getOriginalFilename());
                
                        // Processar os outros campos do formulário aqui
                        allParams.forEach((key, value) -> {
                            System.out.println("Received form data: " + key + " = " + value);
                            // Aqui você pode salvar esses dados no banco
                        });
                
                        return ResponseEntity.ok("PDF and form data uploaded successfully!");
                    } catch (Exception e) {
                        e.printStackTrace();
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload PDF and form data");
                    }
                }
                

@GetMapping("/formulario/{id}")
public String mostrarFormulario(@PathVariable Integer id, Model model) {
    Optional<Formulario> formularioOptional = formularioRepo.findById(id);
    List<Fornecedores> fornecedores = fornecedoresRepo.findAll(); // Assegure-se de ter um método findAll em seu fornecedoresRepo
    if (formularioOptional.isPresent()) {
        Formulario formulario = formularioOptional.get();
        model.addAttribute("formulario", formulario);
        model.addAttribute("Fornecedores", fornecedores);
        return "home/IndexC";
    } else {
        return "redirect:/listar-formularios";
    }
}



}