package com.kafein.garage;

import com.kafein.garage.data.Garage;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class GarageApplication {

    public static void main(String[] args) {
        SpringApplication.run(GarageApplication.class, args);
    }

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
    public Garage garage(){
        return new Garage();
    }
}
