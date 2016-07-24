package com.example.products.mongo;

import com.example.products.constants.Constants;
import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.net.UnknownHostException;

/**
 * Created by a036862 on 7/23/16.
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.example.product.repository")
@PropertySource("classpath:config.properties")
public class MongoConfig {
    /**
     * Spring MongoDB configuration file
     *
     */
    @Value("${mongodb.url}")
    private String url;
        @Bean
        public MongoTemplate mongoTemplate() throws UnknownHostException {

            return new MongoTemplate(new MongoClient(url), Constants.PRODUCT);

        }

    }

