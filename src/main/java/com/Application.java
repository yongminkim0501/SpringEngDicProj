package com;  // 루트 패키지

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {  // 또는 프로젝트명Application
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}