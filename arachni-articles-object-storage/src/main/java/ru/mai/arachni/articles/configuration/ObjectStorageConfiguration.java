package ru.mai.arachni.articles.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import ru.mai.arachni.articles.objectstorage.provider.ObjectStorageProvider;
import ru.mai.arachni.articles.objectstorage.service.ObjectStorageService;

@Configuration
@EnableScheduling
public class ObjectStorageConfiguration {

    @Bean
    public ObjectStorageProvider objectStorageProvider(
            @Value("${object-storage.objectsUrl}") String objectStorageUrl,
            RestTemplate restTemplate
    ) {
        return new ObjectStorageProvider(
                objectStorageUrl,
                restTemplate
        );
    }

    @Bean
    public ObjectStorageService objectStorageService(
            ObjectStorageProvider objectStorageProvider
    ) {
        return new ObjectStorageService(
                objectStorageProvider
        );
    }
}
