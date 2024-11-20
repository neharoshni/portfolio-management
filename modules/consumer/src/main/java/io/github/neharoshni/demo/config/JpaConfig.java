package io.github.neharoshni.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "io.github.neharoshni.demo.repositories")
public class JpaConfig {
}