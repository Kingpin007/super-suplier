package com.suplier.super_suplier.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EntityScan("com.suplier.super_suplier.domain")
@EnableJpaRepositories("com.suplier.super_suplier.repos")
@EnableTransactionManagement
public class DomainConfig {
}
