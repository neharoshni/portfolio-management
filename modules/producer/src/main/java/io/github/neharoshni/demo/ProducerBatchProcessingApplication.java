package io.github.neharoshni.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@SpringBootApplication(scanBasePackages = {"io.github.neharoshni.demo"})
@EnableDiscoveryClient
public class ProducerBatchProcessingApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(ProducerBatchProcessingApplication.class);
        System.exit(SpringApplication.exit(application.run(args)));
    }
}
