package org.xry.adminmodule;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"org.xry.adminmodule", "org.xry.interceptors"})
public class AdminModuleApplication {
    public static void main(String[] args) {
        SpringApplication.run(AdminModuleApplication.class, args);
    }

}
