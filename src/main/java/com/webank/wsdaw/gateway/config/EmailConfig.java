package com.webank.wsdaw.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("email")
@Data
public class EmailConfig {
    private String host;
    private int port;
    private boolean sslEnable;
    private String from;
    private String user;
    private String pass;
}
