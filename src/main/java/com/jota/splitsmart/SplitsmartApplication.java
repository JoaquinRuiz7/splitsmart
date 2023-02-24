package com.jota.splitsmart;

import static com.jota.splitsmart.context.ContextData.BASE_PACKAGE;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(scanBasePackages = BASE_PACKAGE, exclude = {SecurityAutoConfiguration.class})
public class SplitsmartApplication {

    public static void main(String[] args) {
        SpringApplication.run(SplitsmartApplication.class, args);
    }

}
