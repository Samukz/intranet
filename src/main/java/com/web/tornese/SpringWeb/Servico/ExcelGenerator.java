package com.web.tornese.SpringWeb.Servico;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.web.tornese.SpringWeb.models.Catalogo;

import java.io.OutputStream;
import java.util.List;


public class ExcelGenerator {

    public static void generateExcel(List<Catalogo> items, OutputStream outputStream) {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Items");
            Row headerRow = sheet.createRow(0);

            // Definindo cabeçalhos
            headerRow.createCell(0).setCellValue("ID");
            headerRow.createCell(1).setCellValue("Patrimonio");
            headerRow.createCell(2).setCellValue("Nome");
            headerRow.createCell(3).setCellValue("Valor");
            headerRow.createCell(4).setCellValue("Data");
            headerRow.createCell(5).setCellValue("Local");
            headerRow.createCell(6).setCellValue("Categoria");
            headerRow.createCell(7).setCellValue("Estado do Bem");
            headerRow.createCell(8).setCellValue("Nº Serie");
            headerRow.createCell(9).setCellValue("Marca");
            headerRow.createCell(10).setCellValue("Modelo");
            // Adicione mais cabeçalhos conforme necessário

            int rowNum = 1;
            for (Catalogo item : items) {
                Row row = sheet.createRow(rowNum++);

                // Preenchendo dados. Certifique-se de que os getters estão corretos.
                row.createCell(0).setCellValue(item.getId());
                // row.createCell(1).setCellValue(item.getPatrimonio());
                // row.createCell(2).setCellValue(item.getNome());
                // row.createCell(3).setCellValue(item.getValor());
                // row.createCell(4).setCellValue(item.getData());
                // row.createCell(5).setCellValue(item.getLocal());
                // row.createCell(6).setCellValue(item.getCategoria());
                // row.createCell(7).setCellValue(item.getEstado());
                // row.createCell(8).setCellValue(item.getSerie());
                row.createCell(9).setCellValue(item.getMarca());
                row.createCell(10).setCellValue(item.getModelo());
                // Continue preenchendo outros dados conforme necessário

                // Verifique no console ou log se os dados estão sendo processados
                // System.out.println("Processando item: ID=" + item.getId() + ", Nome=" + item.getNome());
            }

            // Escrevendo o arquivo Excel
            workbook.write(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
            // Se houver exceção, ela será impressa no console/log. Isso pode ajudar a
            // identificar o problema.
        }
    }
}