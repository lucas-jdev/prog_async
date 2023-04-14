package dev.codelevel.prog_async.infraestrutura.bd.mongo_reactive.dao;

import java.util.Collection;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.codelevel.prog_async.dominio.cliente.gateway.IClienteGateway;
import dev.codelevel.prog_async.dominio.cliente.modelo.Cliente;
import dev.codelevel.prog_async.infraestrutura.bd.mongo_reactive.repositorio.ClienteRepositorio;
import dev.codelevel.prog_async.infraestrutura.bd.mongo_reactive.schema.ClienteSchema;

public class ClienteMongoReactiveDAO implements IClienteGateway{

    private ClienteRepositorio repository;

    Logger log = LoggerFactory.getLogger(ClienteMongoReactiveDAO.class);

    public ClienteMongoReactiveDAO(ClienteRepositorio repo) {
        this.repository = repo;
    }

    @Override
    public void salvar(Cliente cliente, Consumer<Void> callback, Consumer<Throwable> onError) {
        try {
            repository.save(new ClienteSchema(cliente.nome(), cliente.cpf(), cliente.idade()))
                .subscribe(
                    c -> callback.accept(null),
                    e -> onError.accept(e)
                );
        } catch (Exception e) {
            onError.accept(e);
        }
    }

    @Override
    public void buscarPorCpf(String cpf, Consumer<Cliente> callback, Consumer<Throwable> onError) {
        try {
            repository.findByCpf(cpf)
                .map(cliente -> criaCliente(cliente.getNome(), cliente.getCpf(), cliente.getIdade()))
                .subscribe(
                    callback::accept,
                    onError::accept
                );
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    @Override
    public void buscarTodos(Consumer<Collection<Cliente>> callback, Consumer<Throwable> onError) {
        try {
            repository.findAll()
                        .map(c -> criaCliente(c.getNome(), c.getCpf(), c.getIdade()))
                        .collectList()
                        .subscribe(
                            callback::accept,
                            onError::accept
                        );
        } catch (Exception e) {
            onError.accept(e);
        }
    }

    @Override
    public void deletarPorCpf(String cpf, Consumer<Void> callback, Consumer<Throwable> onError) {
        try {
            repository.deleteByCpf(cpf)
                        .subscribe(
                            c -> callback.accept(null),
                            e -> onError.accept(e)
                        );
        } catch (Exception e) {
            onError.accept(e);
        }
    }

    private Cliente criaCliente(String nome, String cpf, int idade){
        return Cliente.criarCliente(Cliente.entradaDadosCliente(nome, cpf, idade));
    }
    
}
