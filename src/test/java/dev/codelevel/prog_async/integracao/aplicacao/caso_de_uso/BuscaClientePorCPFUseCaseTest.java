package dev.codelevel.prog_async.integracao.aplicacao.caso_de_uso;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import dev.codelevel.prog_async.aplicacao.caso_de_uso.cliente.BuscaClientePorCPFUseCase;
import dev.codelevel.prog_async.dominio.cliente.gateway.IClienteGateway;
import dev.codelevel.prog_async.dominio.cliente.modelo.Cliente;
import dev.codelevel.prog_async.dominio.cliente.modelo.Cliente.DadosCliente;
import dev.codelevel.prog_async.infraestrutura.bd.memoria.ClienteEmMemoriaDAO;

@TestInstance(Lifecycle.PER_CLASS)
class BuscaClientePorCPFUseCaseTest {

    private final IClienteGateway clienteGateway = new ClienteEmMemoriaDAO();

    private final String NOME = "João";
    private final String CPF = "123.456.789-00";
    private final int IDADE = 20;

    private BuscaClientePorCPFUseCase casoDeUso;

    @BeforeAll
    void setUp() {
        DadosCliente dadosCliente = Cliente.entradaDadosCliente(NOME, CPF, IDADE);
        Cliente cliente = Cliente.criarCliente(dadosCliente);
        clienteGateway.salvar(
            cliente,
            callback ->  {}, 
            erro -> {}
        );

        this.casoDeUso = BuscaClientePorCPFUseCase.init(clienteGateway);
    }

    @Test
    void testInit() {
        BuscaClientePorCPFUseCase casoDeUso = BuscaClientePorCPFUseCase.init(clienteGateway);
        assertNotNull(casoDeUso);
    }

    @Test
    void buscarClientePorCPFNoGatewayNaoDeveLancarExcecao() {
        assertDoesNotThrow(() -> {
            casoDeUso.executar(
                CPF,
                callback -> {},
                erro -> {}
            );
        });
    }

    @Test
    void buscarClientePorCPFNoGatewayDeveRetornarCliente() {
        casoDeUso.executar(
            CPF,
            callback -> {
                assertNotNull(callback);
            },
            erro -> {}
        );
    }

    @Test
    void buscarClientePorCPFInexistenteNoGatewayNaoDeveLancarExcecao() {
        assertDoesNotThrow(() -> {
            casoDeUso.executar(
                "000.000.000-00",
                callback -> {},
                erro -> {}
            );
        });
    }

    @Test
    void buscarClientePorCPFInexistenteNoGatewayDeveRetornarNull() {
        casoDeUso.executar(
            "000.000.000-00",
            callback -> {
                assertNull(callback);
            },
            erro -> {}
        );
    }

}
