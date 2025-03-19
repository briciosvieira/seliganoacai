package com.seliganoacai.acai.service;

import com.google.cloud.storage.Bucket;
import com.google.firebase.cloud.StorageClient;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Service
public class FirebaseService {

    public String upload(MultipartFile imageFile, String imageName) throws IOException {
        InputStream inputStream = imageFile.getInputStream();
        Bucket bucket = StorageClient.getInstance().bucket();
        String uniqueFileName = UUID.randomUUID() +"_"+imageName;
        bucket.create(uniqueFileName, inputStream, imageFile.getContentType());

        return "https://firebasestorage.googleapis.com/v0/b/" + bucket.getName() + "/o/"
                + URLEncoder.encode(uniqueFileName, StandardCharsets.UTF_8) + "?alt=media";
    }
}
