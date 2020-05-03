package com.github.qjerry;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <p>Title:API-CodeBox</p>
 * <p>Desc: 错误码内部API</p>
 *
 * @author Jerry
 * @version 1.0
 * @since 2020/4/3
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.github.qjerry.mapper")
@EnableFeignClients(basePackages = {"com.github.qjerry"})
@ServletComponentScan
public class CodeBoxApiServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CodeBoxApiServiceApplication.class, args);
    }
}
