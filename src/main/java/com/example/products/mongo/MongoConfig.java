package com.example.products.mongo;

import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

/**
 * Created by a036862 on 7/23/16.
 */
@Configuration
@EnableMongoRepositories(basePackages = "com.example.product.repository")
public class MongoConfig {
    /**
     * Spring MongoDB configuration file
     *
     */
        @Bean
        public MongoTemplate mongoTemplate() throws Exception {

            return new MongoTemplate(new MongoClient("127.0.0.1"),"product");

        }

    }

