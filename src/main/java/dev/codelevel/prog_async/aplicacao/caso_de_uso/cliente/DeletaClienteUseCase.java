package dev.codelevel.prog_async.aplicacao.caso_de_uso.cliente;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.codelevel.prog_async.dominio.cliente.gateway.IClienteGateway;

public class DeletaClienteUseCase {

    private final Logger log = LoggerFactory.getLogger(DeletaClienteUseCase.class);

    private final IClienteGateway clienteGateway;

    private DeletaClienteUseCase() {
        throw new UnsupportedOperationException("Não é permitido criar instâncias de DeletaClienteUseCase");
    }

    private DeletaClienteUseCase(IClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    public static DeletaClienteUseCase init(IClienteGateway clienteGateway) {
        return new DeletaClienteUseCase(clienteGateway);
    }

    public void executar(EntradaDeletaClienteUseCase request) {
        clienteGateway.deletarPorCpf(
            request.cpf,
            callback -> log.info("Cliente deletado com sucesso"),
            erro -> log.error("Erro ao deletar cliente")
        );
    }

    public static EntradaDeletaClienteUseCase dadosEntrada(String cpf) {
        return new EntradaDeletaClienteUseCase(cpf);
    }

    public record EntradaDeletaClienteUseCase(String cpf) {
    }

}
