package com.seliganoacai.acai.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.IOException;

@Configuration
public class SpringFirebaseConfig {

    @Bean
    public FirebaseApp initializeFirebase() throws IOException {
        String serviceAccountPath = System.getProperty("user.dir") + "/spring-firebase-key.json";
        FileInputStream serviceAccountStream = new FileInputStream(serviceAccountPath);

        FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccountStream))
                .setStorageBucket("image-seliganoacai.firebasestorage.app")
                .build();
        return FirebaseApp.initializeApp(options);
    }
}
