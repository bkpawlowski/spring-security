package pl.decerto.rekrutacja.bpawlowski;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
class HelloServiceStarter {
    public static void main(String[] args) {
        new SpringApplication(HelloServiceStarter.class).run(args);
    }
}