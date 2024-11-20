package io.github.neharoshni.demo.config;

import io.github.neharoshni.demo.services.PortfolioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class ServicesConfig {
    @Bean
    public PortfolioService portfolioService() {
        return new PortfolioService();
    }
}