package com.example.trelloapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrelloAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrelloAppApplication.class, args);
    }
    //для теста контроллеров используем @WebMvcTest
    //для теста репозиториев используем @DataJpaTest
    //для теста сервисов используем @SpringBootTest

}
