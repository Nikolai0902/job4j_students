package ru.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication
public class ServS {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ServS.class);
        application.addListeners(new ApplicationPidFileWriter("./serv_s.pid"));
        application.run();
    }
}
