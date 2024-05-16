package com.web.tornese.SpringWeb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.layout.LayoutArea;
import com.itextpdf.layout.property.AreaBreakType;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.layout.property.VerticalAlignment;
import com.itextpdf.layout.renderer.DocumentRenderer;
import com.itextpdf.layout.renderer.IRenderer;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.LineSeparator;
import com.itextpdf.io.IOException;
import com.itextpdf.io.font.FontConstants;
import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;


import com.web.tornese.SpringWeb.models.Catalogo;
import com.web.tornese.SpringWeb.repositorio.CatalogoRepo;

@Controller
public class DownloadController {

    @Autowired
    private CatalogoRepo catalogoRepo; // Injeção do repositório

    @GetMapping("/download-catalogo")
    public ResponseEntity<InputStreamResource> downloadCatalogo() throws Exception {
        ByteArrayInputStream bis = gerarCatalogoPDF();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=catalogo.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }

    private ByteArrayInputStream gerarCatalogoPDF() throws Exception {
        List<Catalogo> produtos = (List<Catalogo>) catalogoRepo.findAll();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4);
        document.setMargins(20, 20, 20, 20);

        FooterHandler footerHandler = new FooterHandler(pdf, 40); // 40 é a altura do rodapé
        pdf.addEventHandler(PdfDocumentEvent.END_PAGE, footerHandler);

        boolean first = true;
        for (Catalogo produto : produtos) {
            if (!first) {
                document.add(new AreaBreak(AreaBreakType.NEXT_PAGE));
            }
            first = false;
            adicionarProduto(document, produto);
        }

        document.close();
        return new ByteArrayInputStream(out.toByteArray());
    }

    private void adicionarProduto(Document document, Catalogo produto) throws Exception {
        // Adicionar o conteúdo de cada produto aqui. O exemplo abaixo é simplificado.
        

        // Primeiro, assegure-se de que uma nova página esteja pronta para adicionar
        // conteúdo
        if (document.getPdfDocument().getNumberOfPages() == 0) {
            document.getPdfDocument().addNewPage();
        }
        PdfPage page = document.getPdfDocument().getLastPage();
        PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), document.getPdfDocument());

        // Define o caminho e carrega a imagem de fundo
        ImageData backgroundImageData = ImageDataFactory
                .create("https://storage.googleapis.com/imagens_doit/img-uploads-catalogo/doitfundo.png");

        // Define as dimensões e posição central na página
        float width = 150;
        float height = 150;
        float xCenter = (PageSize.A4.getWidth() - width) / 2;
        float yCenter = (PageSize.A4.getHeight() - height) / 2;

        // Subtrai um valor adicional para deslocar a imagem para baixo
        float deslocamento = 100; // Ajuste este valor conforme necessário
        yCenter -= deslocamento;

        // Adiciona a imagem de fundo
        canvas.addImage(backgroundImageData, xCenter, yCenter, width, false);


        // Adicionando uma tabela com 2 colunas para as imagens
        float[] columnWidths = { 1, 3, 1 }; // Define a proporção das colunas (ambas com a mesma largura aqui)
        Table table = new Table(columnWidths);

        table.setMarginBottom(-0.5f);

        // Adicionar a primeira imagem
        Image img1 = new Image(
                ImageDataFactory
                        .create("https://storage.googleapis.com/imagens_doit/img-uploads-catalogo/DOIT.png"));
        img1.scaleToFit(80, 80); // Ajusta o tamanho da imagem para caber na célula
        img1.setHorizontalAlignment(HorizontalAlignment.CENTER); // Alinha horizontalmente ao centro
        Cell cell1 = new Cell().add(img1);
        cell1.setPadding(10); // Adiciona padding interno para a célula
        table.addCell(cell1);

        // Criar a nova célula com texto
        Paragraph paragraph = new Paragraph();

        // Configurar a fonte
        PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD); // Usa Helvetica Negrito

        // Configurar a fonte
        PdfFont font2 = PdfFontFactory.createFont(FontConstants.HELVETICA); // Usa Helvetica Negrito

        // Criar textos com a fonte e tamanho configurados
        Text text1 = new Text("DO IT").setFont(font).setFontSize(16); 

        Text text3 = new Text("APOIO ADMINISTRATIVO E CONSULTORIA").setFont(font2).setFontSize(11);

        Text text4 = new Text("EM LOGÍSTICA").setFont(font2).setFontSize(11);


        Text text2 = new Text("OSS VIVA RIO").setFont(font).setFontSize(16); // Define o tamanho da fonte para 14

        // Adicionar textos ao parágrafo com quebra de linha entre eles
        paragraph.add(text1)
                .add("\n") // Adiciona uma quebra de linha
                .add(text3)
                        .add("\n") // Adiciona uma quebra de linha
                .add(text4)
                        .add("\n") // Adiciona uma quebra de linha
                .add(text2)
                .setTextAlignment(TextAlignment.CENTER); // Centraliza o texto no parágrafo

        // Criar a célula e adicionar o parágrafo
        Cell cell3 = new Cell().add(paragraph);
        cell3.setPadding(10); // Adicionar padding à nova célula
        cell3.setVerticalAlignment(VerticalAlignment.MIDDLE); // Alinha verticalmente ao meio
        cell3.setHorizontalAlignment(HorizontalAlignment.CENTER); // Alinha horizontalmente ao centro
        cell3.setWidth(350); // Define a largura da célula em unidades (por exemplo, pontos)

        table.addCell(cell3);

        // Adicionar a segunda imagem
        Image img2 = new Image(ImageDataFactory
                .create("https://storage.googleapis.com/imagens_doit/img-uploads-catalogo/VIVA%20RIO%20LOGO.png"));
        img2.scaleToFit(80, 80); // Ajusta o tamanho da imagem
        img2.setHorizontalAlignment(HorizontalAlignment.CENTER);
        Cell cell2 = new Cell().add(img2);
        cell2.setPadding(10); // Adiciona padding para a célula
        table.addCell(cell2);

        document.add(table); // Adiciona a tabela ao documento

        // // Adiciona uma linha em branco (espaço) após a tabela
        // Paragraph blankLine = new Paragraph("\n"); // Cria um parágrafo vazio
        // blankLine.setMarginTop(1); // Define o margem superior do parágrafo para
        // criar o espaço
        // document.add(blankLine); // Adiciona o parágrafo vazio ao documento

        // Configuração da nova tabela com duas colunas
        float[] columnSizes = { 1, 1 }; // Define as larguras das colunas como iguais
        Table additionalTable = new Table(columnSizes); // Cria a tabela com
                                                        // proporções definidas

        // Criar e adicionar a primeira célula na nova tabela
        Paragraph paragraphOne = new Paragraph("CATÁLOGO DE ESPECIFICAÇÃO DE INSUMOS")
                .setTextAlignment(TextAlignment.CENTER); // Assegura o alinhamento do texto para o centro

        Cell firstCell = new Cell()
                .add(paragraphOne)
                .setPadding(10) // Define o padding da célula
                .setVerticalAlignment(VerticalAlignment.MIDDLE) // Define o alinhamento vertical no meio
                .setHorizontalAlignment(HorizontalAlignment.CENTER) // Define o alinhamento horizontal no centro
                .setWidth(350) // Define a largura da célula em unidades (por exemplo, pontos)
                .setHeight(22); // Define a altura da célula

        additionalTable.addCell(firstCell); // Adiciona a célula à tabela

        // Criar e adicionar a segunda célula na nova tabela
        Paragraph paragraphTwo = new Paragraph(produto.getGrupo())
                .setTextAlignment(TextAlignment.CENTER); // Centraliza o texto do parágrafo horizontalmente

        Cell secondCell = new Cell()
                .add(paragraphTwo)
                .setPadding(10) // Define o padding da célula
                .setVerticalAlignment(VerticalAlignment.MIDDLE) // Define o alinhamento vertical no meio
                .setHorizontalAlignment(HorizontalAlignment.CENTER) // Define o alinhamento horizontal no centro
                .setWidth(200); // Define a largura da célula em unidades (por exemplo, pontos)
            
        additionalTable.addCell(secondCell); // Adiciona a célula à tabela

        // Adicionar a nova tabela ao documento
        document.add(additionalTable);

        // Adiciona uma linha em branco (espaço) após a tabela
        Paragraph blankLine = new Paragraph("\n"); // Cria um parágrafo vazio
        blankLine.setMarginTop(1); // Define o margem superior do parágrafo para criar o espaço
        document.add(blankLine); // Adiciona o parágrafo vazio ao documento

        // Configuração da nova tabela com três colunas igualmente dimensionadas
        float[] columnSizes2 = { 1, 5, 1 }; // Define as larguras das colunas como iguais
        Table additionalTable2 = new Table(UnitValue.createPercentArray(columnSizes2)).useAllAvailableWidth(); // Usa
                                                                                                               // toda a
                                                                                                               // largura
                                                                                                               // disponível

                                additionalTable2.setMarginBottom(-0.5f);
                           // Criar uma fonte em negrito
        PdfFont fontBold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

        // Criar e adicionar a primeira célula na nova tabela
        Paragraph paragraphOne2 = new Paragraph("CÓDIGO")
                        .setFont(fontBold) // Define a fonte como negrito
                        .setFontSize(10) // Define o tamanho da fonte para 10
                        .setTextAlignment(TextAlignment.CENTER); // Centraliza o texto do parágrafo horizontalmente

        Cell firstCell2 = new Cell()
                        .add(paragraphOne2)
                        .setPadding(5) // Define o padding para 5 unidades
                        .setVerticalAlignment(VerticalAlignment.MIDDLE) // Alinha verticalmente no meio
                        .setHorizontalAlignment(HorizontalAlignment.CENTER) // Alinha horizontalmente no centro
                        .setHeight(20); // Define a altura da célula para 20 unidades

        additionalTable2.addCell(firstCell2); // Adiciona a célula à tabela

        // Criar e adicionar a segunda célula na nova tabela
        Paragraph paragraphTwo2 = new Paragraph("ITEM")
                        .setFont(fontBold) // Aplica a fonte em negrito
                        .setFontSize(10) // Aplica o tamanho de fonte 10
                        .setTextAlignment(TextAlignment.CENTER); // Centraliza o texto do parágrafo horizontalmente

        Cell secondCell2 = new Cell()
                        .add(paragraphTwo2)
                        .setPadding(5) // Aplica padding de 5 unidades
                        .setVerticalAlignment(VerticalAlignment.MIDDLE) // Alinha verticalmente no meio
                        .setHorizontalAlignment(HorizontalAlignment.CENTER) // Alinha horizontalmente no centro
                        .setHeight(20); // Define a altura da célula em 20 unidades

        additionalTable2.addCell(secondCell2); // Adiciona a célula à tabela

        // Criar e adicionar a terceira célula na nova tabela
        Paragraph paragraphThree3 = new Paragraph("UNIDADE\nDE MEDIDA")
                        .setFont(fontBold) // Aplica a fonte em negrito
                        .setFontSize(8) // Aplica o tamanho de fonte 8
                        .setTextAlignment(TextAlignment.CENTER); // Centraliza o texto do parágrafo horizontalmente,
                                                                 // considerando a quebra de linha

        Cell thirdCell3 = new Cell()
                        .add(paragraphThree3)
                        .setPadding(3) // Reduz o padding para 3 unidades
                        .setVerticalAlignment(VerticalAlignment.MIDDLE) // Alinha verticalmente no meio
                        .setHorizontalAlignment(HorizontalAlignment.CENTER) // Alinha horizontalmente no centro
                        .setHeight(25); // Define a altura da célula em 25 unidades para acomodar duas linhas

        additionalTable2.addCell(thirdCell3); // Adiciona a célula à tabela

        // Adicionar a nova tabela ao documento
        document.add(additionalTable2);

        // Configuração da nova tabela com três colunas igualmente dimensionadas
        float[] columnWidths3 = { 1, 5, 1 }; // Define as proporções das colunas
        Table productTable = new Table(UnitValue.createPercentArray(columnWidths3)).useAllAvailableWidth(); // Usa toda
                                                                                                            // a largura
                                                                                                            // disponível

        productTable.setMarginBottom(-0.5f);

        // Criar e adicionar a primeira célula na tabela para o código do produto
        Paragraph paragraphProduct = new Paragraph(produto.getCodigo())
                        .setFontSize(10) // Reduz o tamanho da fonte para 10
                        .setTextAlignment(TextAlignment.CENTER); // Centraliza o texto do parágrafo horizontalmente

        Cell cellProduct = new Cell().add(paragraphProduct)
                        .setPadding(5) // Define o padding de 5 unidades
                        .setVerticalAlignment(VerticalAlignment.MIDDLE) // Alinha verticalmente no meio
                        .setHorizontalAlignment(HorizontalAlignment.CENTER); // Alinha horizontalmente no centro

        productTable.addCell(cellProduct); // Adiciona a célula à tabela

        // Criar e adicionar a segunda célula na tabela para o item do produto
        Paragraph paragraphPrice = new Paragraph(produto.getItem())
                        .setFontSize(10) // Aplica o mesmo tamanho de fonte 10
                        .setTextAlignment(TextAlignment.CENTER); // Centraliza o texto do parágrafo horizontalmente

        Cell cellPrice = new Cell().add(paragraphPrice)
                        .setPadding(5) // Aplica o mesmo padding de 5 unidades
                        .setVerticalAlignment(VerticalAlignment.MIDDLE) // Alinha verticalmente no meio
                        .setHorizontalAlignment(HorizontalAlignment.CENTER); // Alinha horizontalmente no centro

        productTable.addCell(cellPrice); // Adiciona a célula à tabela

        // Criar e adicionar a terceira célula na tabela
        Paragraph paragraphQuantity = new Paragraph(produto.getUnmedida())
                        .setFontSize(10) // Aplica o tamanho de fonte 10
                        .setTextAlignment(TextAlignment.CENTER); // Centraliza o texto do parágrafo horizontalmente

        Cell cellQuantity = new Cell().add(paragraphQuantity)
                        .setPadding(5) // Define o padding de 5 unidades
                        .setVerticalAlignment(VerticalAlignment.MIDDLE) // Alinha verticalmente no meio
                        .setHorizontalAlignment(HorizontalAlignment.CENTER); // Alinha horizontalmente no centro

        productTable.addCell(cellQuantity); // Adiciona a célula à tabela

        // Adicionar a nova tabela ao documento
        document.add(productTable);
        // Configuração da nova tabela com duas colunas de larguras desproporcionais
        float[] columnWidths4 = { 1, 6 }; // Ajusta as proporções para que a segunda coluna seja mais larga
        Table summaryTable = new Table(UnitValue.createPercentArray(columnWidths4)).useAllAvailableWidth(); // Usa toda
                                                                                                            // a largura
                                                                                                            // disponível
                summaryTable.setMarginBottom(-0.5f);

        
                // Criar fonte em negrito
                PdfFont fontBold2 = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

                // Criar um parágrafo com texto em negrito
                Paragraph paragraphDescription = new Paragraph()
                                .setFont(fontBold2) // Define a fonte como negrito
                                .setFontSize(10) // Define o tamanho da fonte para 10
                                .add(new Text("GRUPO").setTextAlignment(TextAlignment.CENTER)); // Adiciona o texto
                                                                                                // "GRUPO" e
                                                                                                // centraliza-o
                                                                                                // horizontalmente no
                                                                                                // parágrafo

                Cell cellDescription = new Cell()
                                .add(paragraphDescription)
                                .setPadding(5) // Define o padding para 5 unidades
                                .setVerticalAlignment(VerticalAlignment.MIDDLE) // Alinha verticalmente no meio
                                .setHorizontalAlignment(HorizontalAlignment.CENTER) // Alinha horizontalmente no centro
                                .setHeight(20); // Define a altura da célula para 20 unidades

                summaryTable.addCell(cellDescription); // Adiciona a célula à tabela

        // Criar e adicionar a segunda célula na tabela
        Paragraph paragraphValue = new Paragraph(produto.getGrupo())
                .setFontSize(10); // Aplica o mesmo tamanho de fonte reduzido
        Cell cellValue = new Cell().add(paragraphValue)
                .setPadding(5) // Aplica o mesmo padding reduzido
                .setVerticalAlignment(VerticalAlignment.MIDDLE) // Mantém o alinhamento vertical
                .setHorizontalAlignment(HorizontalAlignment.CENTER) // Mantém o alinhamento horizontal
                .setHeight(20); // Força a mesma altura fixa

        summaryTable.addCell(cellValue);

        // Adicionar a nova tabela ao documento
        document.add(summaryTable);


        ///--------------- proxima tabela-----//

        // Configuração da nova tabela com duas colunas de larguras desproporcionais
        float[] columnWidths5 = { 1, 6 }; // Ajusta as proporções para que a segunda coluna seja mais larga
        Table contactTable = new Table(UnitValue.createPercentArray(columnWidths5)).useAllAvailableWidth(); // Usa toda
                                                                                                            // a largura
                                                                                                            // disponível

        // Criar e adicionar a primeira célula na tabela
        Paragraph paragraphRole = new Paragraph()
                        .setFont(fontBold2) // Define a fonte como negrito
                        .setFontSize(10) // Define o tamanho da fonte para 10
                        .add(new Text("SUBGRUPO").setTextAlignment(TextAlignment.CENTER)); // Adiciona o texto
                                                                                           // "SUBGRUPO" e centraliza-o
                                                                                           // horizontalmente no
                                                                                           // parágrafo

        Cell cellRole = new Cell()
                        .add(paragraphRole)
                        .setPadding(5) // Define o padding para 5 unidades
                        .setVerticalAlignment(VerticalAlignment.MIDDLE) // Alinha verticalmente no meio
                        .setHorizontalAlignment(HorizontalAlignment.CENTER) // Alinha horizontalmente no centro
                        .setHeight(20); // Define a altura da célula para 20 unidades

        contactTable.addCell(cellRole); // Adiciona a célula à tabela

        // Criar e adicionar a segunda célula na tabela
        Paragraph paragraphContactInfo = new Paragraph(produto.getSubgrupo())
                .setFontSize(10); // Aplica o mesmo tamanho de fonte reduzido
        Cell cellContactInfo = new Cell().add(paragraphContactInfo)
                .setPadding(5) // Aplica o mesmo padding reduzido
                .setVerticalAlignment(VerticalAlignment.MIDDLE) // Mantém o alinhamento vertical
                .setHorizontalAlignment(HorizontalAlignment.CENTER) // Mantém o alinhamento horizontal
                .setHeight(20); // Força a mesma altura fixa

        contactTable.addCell(cellContactInfo);

        // Adicionar a nova tabela ao documento
        document.add(contactTable);
        
        document.add(new Paragraph(" "));

        // Criar e adicionar a segunda célula na tabela
        Paragraph paragraphContactInfo3 = new Paragraph("ESPECIFICAÇÃO")
                .setFontSize(11); // Aplica o mesmo tamanho de fonte reduzido

        document.add((paragraphContactInfo3));

        // document.add(new Paragraph(produto.getEspecificacao()));

        // Criar e adicionar a segunda célula na tabela
        Paragraph paragraphContactInfo2 = new Paragraph(produto.getEspecificacao())
                .setFontSize(10); // Aplica o mesmo tamanho de fonte reduzido

        document.add((paragraphContactInfo2));

        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        document.add(new Paragraph(" "));
        
        // Image image2 = new Image(ImageDataFactory
        //         .create("https://storage.googleapis.com/imagens_doit/img-uploads-catalogo/doitfundo.png"));
        // image2.scaleToFit(150, 150); // Ajusta o tamanho da imagem de fundo
        // image2.setHorizontalAlignment(HorizontalAlignment.CENTER); // Centraliza a imagem de fundo
        // document.add(image2); // Adiciona a imagem de fundo ao documento primeiro

        // Outras adições ao documento como tabelas, textos, etc.

        // Agora adiciona a imagem principal por cima do fundo
        Image image = new Image(ImageDataFactory.create(produto.getCaminhoDaImagem()));
        image.scaleToFit(150, 150); // Ajusta o tamanho da imagem principal
        image.setHorizontalAlignment(HorizontalAlignment.CENTER); // Centraliza a imagem principal
        document.add(image); // Adiciona a imagem principal por cima do fundo
        


        


    }
}

class FooterHandler implements IEventHandler {
    protected PdfDocument pdf;
    protected float footerHeight;

    public FooterHandler(PdfDocument pdf, float footerHeight) {
        this.pdf = pdf;
        this.footerHeight = footerHeight;
    }

    @Override
    public void handleEvent(Event event) {
        PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
        PdfDocument pdfDoc = docEvent.getDocument();
        PdfPage page = docEvent.getPage();
        int pageNumber = pdfDoc.getPageNumber(page);
        Rectangle pageSize = page.getPageSize();
        float x = pageSize.getRight() - 50; // Posiciona a numeração 50 unidades à esquerda do canto direito
        float y = pageSize.getBottom() + 10; // Posiciona 10 unidades acima da parte inferior da página

        PdfCanvas canvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);
        try {
            PdfFont font = PdfFontFactory.createFont();
            String text = "Documento de propriedade da Do It Logística e da OSS Viva Rio, não podendo ser cedido ou copiado sem autorização.";
            float fontSize = 9;
            float textWidth = font.getWidth(text, fontSize);
            float startX = pageSize.getWidth() / 2 - textWidth / 2;

            canvas.beginText()
                    .setFontAndSize(font, fontSize)
                    .moveText(startX, y + 20) // Ajusta a posição do texto de rodapé
                    .showText(text)
                    .endText();

            // Adicionando a numeração de página
            canvas.beginText()
                    .setFontAndSize(font, 12) // Tamanho da fonte para a numeração de páginas
                    .moveText(x, y) // Move para a posição calculada
                    .showText(String.valueOf(pageNumber))
                    .endText();
        } catch (IOException e) {
            System.err.println("Erro ao criar fonte: " + e.getMessage());
        } catch (java.io.IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // Código existente para desenhar a linha
        float lineMargin = 50;
        float lineThickness = 1.5f;
        canvas.setStrokeColor(new DeviceRgb(103, 180, 180))
                .setLineWidth(lineThickness)
                .moveTo(pageSize.getLeft() + lineMargin, y + 30)
                .lineTo(pageSize.getRight() - lineMargin, y + 30)
                .stroke();
    }

}
