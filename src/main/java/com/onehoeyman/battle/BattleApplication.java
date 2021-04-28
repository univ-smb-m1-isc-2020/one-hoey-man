package com.onehoeyman.battle;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Arrays;

@SpringBootApplication
@EntityScan("com.onehoeyman.battle.Entity")
@EnableJpaRepositories("com.onehoeyman.battle.Repository")
@EnableScheduling
public class BattleApplication {

    public static void main(String[] args) {
        SpringApplication.run(BattleApplication.class, args);
    }

    /*@Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            *//*String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }*//*

        };
    }*/

}
