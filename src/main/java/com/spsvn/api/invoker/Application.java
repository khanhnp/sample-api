package com.spsvn.api.invoker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;

@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = { "com.spsvn.api", "io.swagger.configuration" })
@EnableJpaRepositories( basePackages = { "com.spsvn.api.domain.repository" })
@EntityScan(basePackages = { "com.spsvn.api.domain.model" })
public class Application implements CommandLineRunner {

    @Autowired
    DataSource dataSource;

    @Override
    public void run(String... arg0) throws Exception {
        if (arg0.length > 0 && arg0[0].equals("exitcode")) {
            throw new ExitException();
        }
    }

    public static void main(String[] args) throws Exception {
        new SpringApplication(Application.class).run(args);
    }

    class ExitException extends RuntimeException implements ExitCodeGenerator {
        private static final long serialVersionUID = 1L;

        @Override
        public int getExitCode() {
            return 10;
        }

    }
}
