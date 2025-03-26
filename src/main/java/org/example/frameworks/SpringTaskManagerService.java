package org.example.frameworks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringTaskManagerService {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringTaskManagerService.class, args);
    }

}
