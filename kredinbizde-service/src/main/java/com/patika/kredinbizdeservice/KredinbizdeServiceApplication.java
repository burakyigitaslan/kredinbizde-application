package com.patika.kredinbizdeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@EnableCaching
public class KredinbizdeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(KredinbizdeServiceApplication.class, args);

        //IUserService userService = new UserService();

        //IUserService userService2 = new UserService2();

        //UserController userController = new UserController(userService);
       // userController.create(new User());
    }

    /*
    @Bean
    @Scope("prototype")
    public IUserService userService() {
        return new UserService();
    }*/

}
