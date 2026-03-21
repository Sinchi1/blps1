package org.truskovski;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BankMain {

    public static void main(String[] args) {
        SpringApplication.run(BankMain.class, args);
    }
}
