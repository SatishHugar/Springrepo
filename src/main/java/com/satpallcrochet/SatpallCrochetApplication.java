package com.satpallcrochet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.satpallcrochet")
public class SatpallCrochetApplication {

    public static void main(String[] args) {
        SpringApplication.run(SatpallCrochetApplication.class, args);
    }

}
