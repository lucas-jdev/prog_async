package dev.codelevel.prog_async.aplicacao.caso_de_uso.cliente;

import java.util.function.Consumer;

import dev.codelevel.prog_async.dominio.cliente.gateway.IClienteGateway;

public class BuscaClientePorCPFUseCase {

    private final IClienteGateway clienteGateway;

    private BuscaClientePorCPFUseCase() {
        throw new UnsupportedOperationException("Não é permitido criar instâncias de BuscaClientePorCpfUseCase");
    }

    private BuscaClientePorCPFUseCase(IClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    public static BuscaClientePorCPFUseCase init(IClienteGateway clienteGateway) {
        return new BuscaClientePorCPFUseCase(clienteGateway);
    }

    public void executar(String cpf, Consumer<SaidaBuscaClientePorCpfUseCase> callback, Consumer<Throwable> onError) {
        clienteGateway.buscarPorCpf(
            cpf, 
            cliente -> callback.accept(new SaidaBuscaClientePorCpfUseCase(cliente.nome(), cliente.idade())),
            onError::accept
        );
    }

    public record SaidaBuscaClientePorCpfUseCase(
        String nome,
        int idade
    ) {
    }

}
