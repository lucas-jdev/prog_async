package dev.codelevel.prog_async.infraestrutura.bd.memoria;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;

import dev.codelevel.prog_async.dominio.cliente.gateway.IClienteGateway;
import dev.codelevel.prog_async.dominio.cliente.modelo.Cliente;

public class ClienteEmMemoriaDAO implements IClienteGateway{
 
    private Collection<Cliente> clientes = new ArrayList<>();

    @Override
    public void salvar(Cliente cliente, Consumer<Void> callback, Consumer<Throwable> onError) {
        try{
            clientes.add(cliente);
            callback.accept(null);
        } catch (Exception e) {
            onError.accept(e);
        }
    }

    @Override
    public void buscarPorCpf(String cpf, Consumer<Cliente> callback, Consumer<Throwable> onError) {
        try{
            final var cliente = clientes.stream()
                .filter(c -> c.cpf().equals(cpf))
                .findFirst()
                .orElseThrow(() -> new Exception("Cliente não encontrado"));
            callback.accept(cliente);
        } catch (Exception e) {
            onError.accept(e);
        }
    }

    @Override
    public void buscarTodos(Consumer<Collection<Cliente>> callback, Consumer<Throwable> onError) {
        try{
            callback.accept(clientes);
        } catch (Exception e) {
            onError.accept(e);
        }
    }

    @Override
    public void deletarPorCpf(String cpf, Consumer<Void> callback, Consumer<Throwable> onError) {
        try{
            clientes.removeIf(c -> c.cpf().equals(cpf));
            callback.accept(null);
        } catch (Exception e) {
            onError.accept(e);
        }
    }
    
}
