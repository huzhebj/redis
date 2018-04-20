package com.hualala;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by tianzhen on 2017/10/12.
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class ServiceApplication {

    private static Logger logger = LoggerFactory.getLogger(ServiceApplication.class);


    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = SpringApplication.run(ServiceApplication.class, args);
        logger.info("service start success .....");
    }

}
