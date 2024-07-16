package ru.gb.OpenFaign.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "ru.gb.OpenFaign.proxy")
public class ProjConfig {
}
