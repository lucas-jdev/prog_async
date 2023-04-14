package dev.codelevel.prog_async.unitario.dominio;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import dev.codelevel.prog_async.dominio.cliente.excecoes.ExcecaoCPFInvalido;
import dev.codelevel.prog_async.dominio.cliente.excecoes.ExcecaoIdadeInvalida;
import dev.codelevel.prog_async.dominio.cliente.excecoes.ExcecaoNomeInvalido;
import dev.codelevel.prog_async.dominio.cliente.modelo.Cliente;
import dev.codelevel.prog_async.dominio.cliente.modelo.Cliente.DadosCliente;

class ClienteTest {

    private final String NOME = "Lucas";
    private final String CPF = "111.111.111-11";
    private final int IDADE = 20;

    // regras de negócio

    @ParameterizedTest
    @CsvSource({
            "null",
            "''",
            "' '"
    })
    void cpfInvalidoDeveRetornarExcecao(String cpf) {
        cpf = validaSeNullEstaEmTexto(cpf);
        DadosCliente dados = Cliente.entradaDadosCliente(NOME, cpf, IDADE);
        assertThrows(ExcecaoCPFInvalido.class, () -> Cliente.criarCliente(dados));
    }

    private String validaSeNullEstaEmTexto(String valor) {
        return valor.equals("null") ? null : valor;
    }

    @ParameterizedTest
    @CsvSource({
            "null",
            "''",
            "' '"
    })
    void nomeInvalidoDeveRetornarExcecao(String nome) {
        nome = validaSeNullEstaEmTexto(nome);
        DadosCliente dados = Cliente.entradaDadosCliente(nome, CPF, IDADE);
        assertThrows(ExcecaoNomeInvalido.class, () -> Cliente.criarCliente(dados));
    }

    @ParameterizedTest
    @ValueSource(ints = { 15, 16, 17 })
    void menorDeIdade(int idade) {
        DadosCliente dados = Cliente.entradaDadosCliente(NOME, CPF, idade);
        assertThrows(ExcecaoIdadeInvalida.class, () -> Cliente.criarCliente(dados));
    }

    @ParameterizedTest
    @ValueSource(ints = { 18, 19, 20 })
    void maiorDeIdade(int idade) {
        DadosCliente dados = Cliente.entradaDadosCliente(NOME, CPF, idade);
        Cliente cliente = Cliente.criarCliente(dados);
        assertNotNull(cliente);
    }

    // testes de unidade

    @Test
    void testCriarCliente() {
        DadosCliente dadosCliente = Cliente.entradaDadosCliente(NOME, CPF, IDADE);
        Cliente cliente = Cliente.criarCliente(dadosCliente);

        assertNotNull(cliente);
    }

    @Test
    void testEntradaDadosCliente() {
        DadosCliente dadosCliente = Cliente.entradaDadosCliente(NOME, CPF, IDADE);
        assertNotNull(dadosCliente);
    }

    @Test
    void testToString() {
        DadosCliente dadosCliente = Cliente.entradaDadosCliente(NOME, CPF, IDADE);
        Cliente cliente = Cliente.criarCliente(dadosCliente);

        assertNotNull(cliente.toString());
    }

}
