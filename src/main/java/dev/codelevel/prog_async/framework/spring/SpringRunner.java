package dev.codelevel.prog_async.framework.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import dev.codelevel.prog_async.framework.IFrameworkServer;

@SpringBootApplication
@EnableReactiveMongoRepositories(basePackages = { "dev.codelevel.prog_async.infraestrutura.bd.mongo_reactive.repositorio" })
public class SpringRunner implements IFrameworkServer{

    @Override
    public void start(String[] args) {
        SpringApplication.run(SpringRunner.class, args);
    }
    
}
