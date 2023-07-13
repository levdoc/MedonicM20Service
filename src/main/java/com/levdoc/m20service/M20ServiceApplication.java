package com.levdoc.m20service;

import com.levdoc.m20service.mapper.UserMapper;
import com.levdoc.m20service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class M20ServiceApplication implements CommandLineRunner {

    @Value("${server.port}")
    private String serverPort;

    public static void main(String[] args) {
        SpringApplication.run(M20ServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Application path: http://localhost:" + serverPort);


//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); :TODO - Удалить
//        String hashedPassword = encoder.encode("admin");
//        System.out.println(hashedPassword);

        System.out.println("Its work ...");
    }
}
