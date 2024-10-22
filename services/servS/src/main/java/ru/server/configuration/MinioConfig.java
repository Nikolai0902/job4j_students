package ru.server.configuration;

import io.minio.MinioClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Настройка клиента для взаимодействия с MinIO.
 */
@Configuration
public class MinioConfig {

    @Value("${minio.url}")
    public String minioUrl;

    @Value("${minio.access.key}")
    public String accessKey;

    @Value("${minio.access.secret}")
    public String secretKey;

    @Value("${minio.port}")
    public int port;

    @Bean
    public MinioClient minioClient() {
        return new MinioClient.Builder()
                .credentials(accessKey, secretKey)
                .endpoint(minioUrl, port, false)
                .build();
    }
}
