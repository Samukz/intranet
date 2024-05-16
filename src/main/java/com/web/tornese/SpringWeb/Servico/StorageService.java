package com.web.tornese.SpringWeb.Servico;

import org.springframework.stereotype.Service;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

@Service
public class StorageService {
    private final Storage storage = StorageOptions.getDefaultInstance().getService();

    public void uploadFile(byte[] content, String bucketName, String fileName) {
        try {
            BlobId blobId = BlobId.of(bucketName, fileName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
            Blob blob = storage.create(blobInfo, content);
            System.out.println("Upload successful: " + blob.getName());
        } catch (Exception e) {
            e.printStackTrace(); // Adicione logging aqui para ver o que pode estar errado
            System.out.println("Error during file upload: " + e.getMessage());
        }
    }
}
