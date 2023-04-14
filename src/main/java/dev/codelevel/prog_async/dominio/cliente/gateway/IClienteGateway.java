package dev.codelevel.prog_async.dominio.cliente.gateway;

import java.util.Collection;
import java.util.function.Consumer;

import dev.codelevel.prog_async.dominio.cliente.modelo.Cliente;

public interface IClienteGateway {
    
    void salvar(Cliente cliente, Consumer<Void> callback, Consumer<Throwable> onError);

    void buscarPorCpf(String cpf, Consumer<Cliente> callback, Consumer<Throwable> onError);
    
    void buscarTodos(Consumer<Collection<Cliente>> callback, Consumer<Throwable> onError);

    void deletarPorCpf(String cpf, Consumer<Void> callback, Consumer<Throwable> onError);

}
