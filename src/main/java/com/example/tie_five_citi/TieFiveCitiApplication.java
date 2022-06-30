package com.example.tie_five_citi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class TieFiveCitiApplication {
    public static void main(String[] args) {
        SpringApplication.run(TieFiveCitiApplication.class, args);
    }

}
