package org.microservice.userserviceauth.Configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class bCryptEncoder {

    @Bean
    public BCryptPasswordEncoder getBcryptpasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
