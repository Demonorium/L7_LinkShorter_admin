package com.demonorium;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@ComponentScan
@EnableAutoConfiguration
@Import(SecurityConfig.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class);
    }
}
