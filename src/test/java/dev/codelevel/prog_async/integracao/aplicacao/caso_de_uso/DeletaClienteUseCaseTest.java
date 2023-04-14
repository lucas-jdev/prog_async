package dev.codelevel.prog_async.integracao.aplicacao.caso_de_uso;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import dev.codelevel.prog_async.aplicacao.caso_de_uso.cliente.DeletaClienteUseCase;
import dev.codelevel.prog_async.dominio.cliente.gateway.IClienteGateway;
import dev.codelevel.prog_async.dominio.cliente.modelo.Cliente;
import dev.codelevel.prog_async.dominio.cliente.modelo.Cliente.DadosCliente;
import dev.codelevel.prog_async.infraestrutura.bd.memoria.ClienteEmMemoriaDAO;

@TestInstance(Lifecycle.PER_CLASS)
class DeletaClienteUseCaseTest {

    private final IClienteGateway clienteGateway = new ClienteEmMemoriaDAO();

    private final String NOME = "João";
    private final String CPF = "123.456.789-00";
    private final int IDADE = 20;

    private DeletaClienteUseCase casoDeUso; 

    @BeforeAll
    void setUp() {
        DadosCliente dadosCliente = Cliente.entradaDadosCliente(NOME, CPF, IDADE);
        Cliente cliente = Cliente.criarCliente(dadosCliente);
        clienteGateway.salvar(
            cliente,
            callback ->  {}, 
            erro -> {}
        );
        
        this.casoDeUso = DeletaClienteUseCase.init(clienteGateway);
    }

    @Test
    void testInit() {
        DeletaClienteUseCase casoDeUso = DeletaClienteUseCase.init(clienteGateway);
        assertNotNull(casoDeUso);
    }

    @Test
    void entradaDeletaClienteNaoDeveLancarExcecao() {
        assertDoesNotThrow(() -> DeletaClienteUseCase.dadosEntrada(CPF));
    }

    @Test
    void deletarClienteNoGatewayNaoDeveLancarExcecao() {
        assertDoesNotThrow(() -> {
            casoDeUso.executar(
                DeletaClienteUseCase.dadosEntrada(CPF)
            );
        });
    }

    @Test
    void deletarClienteNoGatewayDeveDeletarCliente() {
        casoDeUso.executar(
            DeletaClienteUseCase.dadosEntrada(CPF)
        );
        clienteGateway.buscarPorCpf(
            CPF,
            cliente -> {
                assertNotNull(cliente);
            },
            erro -> {}
        );
    }
    
}
