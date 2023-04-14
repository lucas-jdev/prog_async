package dev.codelevel.prog_async.aplicacao.caso_de_uso.cliente;

import java.util.Collection;
import java.util.function.Consumer;

import dev.codelevel.prog_async.dominio.cliente.gateway.IClienteGateway;

public class BuscaClientesUseCase {

    private IClienteGateway clienteGateway;

    private BuscaClientesUseCase() {
        throw new UnsupportedOperationException("Não é permitido criar instâncias de BuscaClientes");
    }

    private BuscaClientesUseCase(IClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    public static BuscaClientesUseCase init(IClienteGateway clienteGateway) {
        return new BuscaClientesUseCase(clienteGateway);
    }

    public void executar(Consumer<Collection<SaidaBuscaClientesUseCase>> callback, Consumer<Throwable> onError) {
        try {
            clienteGateway.buscarTodos(
                    clientes -> callback.accept(clientes.stream()
                            .map(cliente -> new SaidaBuscaClientesUseCase(cliente.nome(), cliente.cpf(), cliente.idade()))
                            .toList()),
                    error -> new RuntimeException("Erro ao buscar clientes")
            );
        } catch (Exception e) {
            onError.accept(e);
        }
    }

    public record SaidaBuscaClientesUseCase(
            String nome,
            String cpf,
            int idade) {
    }

}
