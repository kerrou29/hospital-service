/*
package com.example.demo.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner2(UserRepository repository){
        return args -> {
            User salim = new User(
                    "salim21",
                    "s123alim!"

            );

            User khaoula = new User(
                    "khaoula29",
                    "k07haoula!"

            );

            repository.saveAll(
                    List.of(salim, khaoula)
            );

        };
    }
}
*/
