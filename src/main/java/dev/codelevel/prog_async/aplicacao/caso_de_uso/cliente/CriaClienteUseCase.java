package dev.codelevel.prog_async.aplicacao.caso_de_uso.cliente;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import dev.codelevel.prog_async.dominio.cliente.gateway.IClienteGateway;
import dev.codelevel.prog_async.dominio.cliente.modelo.Cliente;

public class CriaClienteUseCase {
    
    Logger log = LoggerFactory.getLogger(CriaClienteUseCase.class);

    private final IClienteGateway clienteGateway;

    private CriaClienteUseCase() {
        throw new UnsupportedOperationException("Não é permitido criar instâncias de CriaClienteUseCase");
    }

    private CriaClienteUseCase(IClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    public static CriaClienteUseCase init(IClienteGateway clienteGateway) {
        return new CriaClienteUseCase(clienteGateway);
    }

    public void executar(EntradaCriaClienteUseCase request) {
        final Cliente.DadosCliente dados;
        dados = Cliente.entradaDadosCliente(request.nome, request.cpf, request.idade);
        Cliente cliente = Cliente.criarCliente(dados);
        clienteGateway.salvar(
            cliente,
            callback -> log.info("Cliente salvo com sucesso"),
            erro -> log.warn("Erro ao salvar cliente")
        );
    }

    public static EntradaCriaClienteUseCase entradaCriaCliente(String nome, String cpf, int idade) {
        return new EntradaCriaClienteUseCase(nome, cpf, idade);
    }

    private record EntradaCriaClienteUseCase(
            String nome,
            String cpf,
            int idade) {
    }

}
