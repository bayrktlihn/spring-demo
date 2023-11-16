package io.bayrktlihn.securitydemo;

import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@SpringBootApplication
public class SecurityDemoApplication implements CommandLineRunner {


    @Autowired
    private List<SecurityFilterChain> filterChainList;

    public static void main(String[] args) {
        SpringApplication.run(SecurityDemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for (SecurityFilterChain securityFilterChain : filterChainList) {
            for (Filter filter : securityFilterChain.getFilters()) {
                System.out.println(filter);
            }
        }
    }
}
