package dev.codelevel.prog_async.framework.spring.container;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import dev.codelevel.prog_async.aplicacao.caso_de_uso.cliente.BuscaClientePorCPFUseCase;
import dev.codelevel.prog_async.aplicacao.caso_de_uso.cliente.BuscaClientesUseCase;
import dev.codelevel.prog_async.aplicacao.caso_de_uso.cliente.CriaClienteUseCase;
import dev.codelevel.prog_async.aplicacao.caso_de_uso.cliente.DeletaClienteUseCase;
import dev.codelevel.prog_async.dominio.cliente.gateway.IClienteGateway;

@Configuration
public class ClienteUseCase {
    
    @Bean
    BuscaClientePorCPFUseCase buscarClientePorCpf(IClienteGateway clienteGateway) {
        return BuscaClientePorCPFUseCase.init(clienteGateway);
    }

    @Bean
    BuscaClientesUseCase buscarClientes (IClienteGateway clienteGateway) {
        return BuscaClientesUseCase.init(clienteGateway);
    }

    @Bean
    CriaClienteUseCase criaCliente (IClienteGateway clienteGateway) {
        return CriaClienteUseCase.init(clienteGateway);
    }

    @Bean
    DeletaClienteUseCase buscaClientePorCpf (IClienteGateway clienteGateway) {
        return DeletaClienteUseCase.init(clienteGateway);
    }

}
