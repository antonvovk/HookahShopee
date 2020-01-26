package com.wolf.hookahshopee;

import com.wolf.hookahshopee.legacy.config.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})
public class HookahShopeeApplication {

    public static void main(String[] args) {
        SpringApplication.run(HookahShopeeApplication.class, args);
    }
}
