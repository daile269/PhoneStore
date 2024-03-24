package com.phonestoreweb.phonestore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
         (exclude = {SecurityAutoConfiguration.class })

public class PhoneStoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhoneStoreApplication.class, args);
    }

}
