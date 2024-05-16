package com.web.tornese.SpringWeb.Servico;

import com.openhtmltopdf.*;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import org.springframework.stereotype.Service;

import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.io.IOException;

@Service
public class PdfGenerationService {

    public byte[] generatePdfFromHtml(String html) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            HtmlConverter.convertToPdf(html, outputStream);
            byte[] pdfBytes = outputStream.toByteArray();
            System.out.println("PDF generated with size: " + pdfBytes.length + " bytes");
            return pdfBytes;
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException("Erro ao gerar PDF", e);
        }
    }
}

