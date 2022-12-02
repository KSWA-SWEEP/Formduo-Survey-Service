package com.sweep.surveyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableDiscoveryClient
@EnableMongoRepositories(basePackages = "com.sweep.surveyservice.domain")
public class SurveyServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SurveyServiceApplication.class, args);
    }

}
