package dev.codelevel.prog_async.integracao.aplicacao.caso_de_uso;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import dev.codelevel.prog_async.aplicacao.caso_de_uso.cliente.CriaClienteUseCase;
import dev.codelevel.prog_async.dominio.cliente.excecoes.ExcecaoNomeInvalido;
import dev.codelevel.prog_async.dominio.cliente.gateway.IClienteGateway;
import dev.codelevel.prog_async.infraestrutura.bd.memoria.ClienteEmMemoriaDAO;

@TestInstance(Lifecycle.PER_CLASS)
class CriaClienteUseCaseTest {
    
    private final IClienteGateway clienteGateway = new ClienteEmMemoriaDAO();

    private final String NOME = "João";
    private final String CPF = "123.456.789-00";
    private final int IDADE = 20;

    private CriaClienteUseCase casoDeUso;

    @BeforeAll
    void setUp() {
        casoDeUso = CriaClienteUseCase.init(clienteGateway);
    }

    @Test
    void testInit() {
        assertNotNull(casoDeUso);
    }

    @Test
    void entradaCriaClienteNaoDeveLancarExcecao() {
        assertDoesNotThrow(() -> CriaClienteUseCase.entradaCriaCliente(NOME, CPF, IDADE));
    }

    @Test
    void salvarClienteNoGatewayNaoDeveLancarExcecao() {
        assertDoesNotThrow(() -> {
            casoDeUso.executar(
                CriaClienteUseCase.entradaCriaCliente(NOME, CPF, IDADE)
            );
        });
    }

    @ParameterizedTest
    @NullAndEmptySource
    void nomeNaoPodeSerNuloOuVazio(String nome) {
        assertThrows(ExcecaoNomeInvalido.class, () -> {
            casoDeUso.executar(
                CriaClienteUseCase.entradaCriaCliente(nome, CPF, IDADE)
            );
        });
    }
    
}
