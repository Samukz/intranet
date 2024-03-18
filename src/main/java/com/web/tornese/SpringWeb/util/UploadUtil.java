package com.web.tornese.SpringWeb.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class UploadUtil {

    public boolean fazerUploadImagem(MultipartFile imagem) {
        if (imagem.isEmpty()) {
            System.out.println("Imagem vazia");
            return false;
        }

        try {
            // Definindo o diretório para upload
            String pastaUploadImagem = "C:\\Users\\samuc\\Desktop\\img";
            Path directoryPath = Paths.get(pastaUploadImagem);
            if (Files.notExists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }

            // Criando o arquivo no diretório
            Path filePath = directoryPath.resolve(imagem.getOriginalFilename());
            File serverFile = filePath.toFile();

            try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile))) {
                stream.write(imagem.getBytes());
            }
            System.out.println("Armazenado");
            return true;

        } catch (Exception e) {
            System.out.println("Falha em carregar");
            return false;
        }
    }
}
