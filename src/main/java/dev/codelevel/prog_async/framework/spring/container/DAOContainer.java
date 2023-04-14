package dev.codelevel.prog_async.framework.spring.container;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.codelevel.prog_async.dominio.cliente.gateway.IClienteGateway;
import dev.codelevel.prog_async.infraestrutura.bd.mongo_reactive.dao.ClienteMongoReactiveDAO;
import dev.codelevel.prog_async.infraestrutura.bd.mongo_reactive.repositorio.ClienteRepositorio;

@Configuration
public class DAOContainer {
    
    @Bean
    IClienteGateway clienteGatway(ClienteRepositorio repo) {
        return new ClienteMongoReactiveDAO(repo);
    }
    
}
