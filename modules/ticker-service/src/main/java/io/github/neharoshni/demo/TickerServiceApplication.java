package io.github.neharoshni.demo;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {"io.github.neharoshni.demo"})
@OpenAPIDefinition(info = @Info(title = "Ticker Service"))
@EnableDiscoveryClient
public class TickerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(TickerServiceApplication.class, args);
    }
}