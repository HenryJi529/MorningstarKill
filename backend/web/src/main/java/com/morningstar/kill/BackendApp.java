package com.morningstar.kill;

import com.morningstar.kill.initializer.DevDataInitializer;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
@MapperScan("com.morningstar.kill.dao.mapper")
public class BackendApp {
    public static void main(String[] args) {
        SpringApplication.run(BackendApp.class, args);
    }
    @Bean
    @Profile("dev")
    CommandLineRunner init(DevDataInitializer devDataInitializer) {
        return args -> {
            devDataInitializer.initializeData(); // 调用数据初始化
        };
    }
}

