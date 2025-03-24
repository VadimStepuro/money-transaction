package com.stepuro.money.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@EntityScan(basePackages = {"com.stepuro.money.transaction.storage.entity"})
@ConfigurationPropertiesScan(basePackages = {"com.stepuro.money.transaction.config"})
@EnableJpaRepositories(basePackages = {"com.stepuro.money.transaction.storage.repository"})
public class MoneyTransactionApplication {
    public static void main(String[] args) {
        SpringApplication.run(MoneyTransactionApplication.class, args);
    }
}
