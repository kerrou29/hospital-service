package com.example.demo.patient;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class PatientConfig {
    @Bean
    CommandLineRunner commandLineRunner(PatientRepository repository){
        return args -> {
            Patient mariam = new Patient(
                    "Mariam",
                    "mariam.j@gmail.com",
                    LocalDate.of(2000, Month.JANUARY, 5)
            );

            Patient omar = new Patient(
                    "Omar",
                    "omar@gmail.com",
                    LocalDate.of(2004, Month.JANUARY, 5)

            );

            repository.saveAll(
                    List.of(mariam, omar)
            );
        };
    }
}
