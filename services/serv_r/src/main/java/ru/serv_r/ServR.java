package ru.serv_r;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication()
public class ServR {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ServR.class);
        application.addListeners(new ApplicationPidFileWriter("./serv_r.pid"));
        application.run();
    }
}
