package dev.codelevel.prog_async.integracao.aplicacao.caso_de_uso;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import dev.codelevel.prog_async.aplicacao.caso_de_uso.cliente.BuscaClientesUseCase;
import dev.codelevel.prog_async.dominio.cliente.gateway.IClienteGateway;
import dev.codelevel.prog_async.dominio.cliente.modelo.Cliente;
import dev.codelevel.prog_async.infraestrutura.bd.memoria.ClienteEmMemoriaDAO;

@TestInstance(Lifecycle.PER_CLASS)
class BuscaClientesUseCaseTest {

    private final IClienteGateway clienteGateway = new ClienteEmMemoriaDAO();

    private Collection<Cliente> clientes;
    BuscaClientesUseCase casoDeUso;

    @BeforeAll
    void setUp() {
        this.clientes = new ArrayList<>();

        clientes.add(Cliente.criarCliente(Cliente.entradaDadosCliente("João", "123.456.789-00", 20)));
        clientes.add(Cliente.criarCliente(Cliente.entradaDadosCliente("José", "234.567.890-00", 25)));
        clientes.add(Cliente.criarCliente(Cliente.entradaDadosCliente("Pedro", "345.678.901-00", 30)));
        clientes.add(Cliente.criarCliente(Cliente.entradaDadosCliente("Paulo", "456.789.012-00", 35)));

        clientes.forEach(cliente -> {
            clienteGateway.salvar(
                cliente,
                callback ->  {}, 
                erro -> {}
            );
        });

        casoDeUso = BuscaClientesUseCase.init(clienteGateway);
    }


    @Test
    void buscarClientesNoGatewayNaoDeveLancarExcecao() {
        assertDoesNotThrow(() -> {
            casoDeUso.executar(
                callback -> {},
                erro -> {}
            );
        });
    }

    @Test
    void buscarClientesNoGatewayDeveRetornarTodosClientes() {
        List<Cliente> lista = new ArrayList<>();
        casoDeUso.executar(
            callback -> {
                callback.stream()
                .map(cliente -> Cliente.criarCliente(Cliente.entradaDadosCliente(cliente.nome(), cliente.cpf(), cliente.idade())))
                .forEach(lista::add);
            },
            erro -> {}
        );
        assertEquals(this.clientes.size(), lista.size());
    }
    
}
