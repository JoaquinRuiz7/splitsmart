package com.jota.splitsmart;

import static com.jota.splitsmart.context.ContextData.BASE_PACKAGE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = BASE_PACKAGE)
public class SplitsmartApplication {

    public static void main(String[] args) {
        SpringApplication.run(SplitsmartApplication.class, args);
    }

}
