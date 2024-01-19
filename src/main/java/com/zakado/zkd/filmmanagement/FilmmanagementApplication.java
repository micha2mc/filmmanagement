package com.zakado.zkd.filmmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FilmmanagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(FilmmanagementApplication.class, args);
    }

}
