package dev.codelevel.prog_async.unitario.dominio;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import dev.codelevel.prog_async.dominio.cliente.excecoes.ExcecaoCPFInvalido;
import dev.codelevel.prog_async.dominio.cliente.excecoes.ExcecaoIdadeInvalida;
import dev.codelevel.prog_async.dominio.cliente.excecoes.ExcecaoNomeInvalido;
import dev.codelevel.prog_async.dominio.cliente.modelo.Cliente;
import dev.codelevel.prog_async.dominio.cliente.modelo.Cliente.DadosCliente;

@DisplayName("Testes de unidade da classe Cliente")
class ClienteTest {

    private final String NOME = "Lucas";
    private final String CPF = "111.111.111-11";
    private final int IDADE = 20;

    @Nested
    @DisplayName("Testes das regras de negócio")
    class RegraDeNegocio{
        
        @ParameterizedTest
        @CsvSource({
                "null",
                "''",
                "' '"
        })
        @DisplayName("Deve lançar ExcecaoCPFInvalido para CPF inválido")
        void cpfInvalidoDeveRetornarExcecao(String cpf) {
            cpf = validaSeNullEstaEmTexto(cpf);
            DadosCliente dados = Cliente.entradaDadosCliente(NOME, cpf, IDADE);
            assertThrows(ExcecaoCPFInvalido.class, () -> Cliente.criarCliente(dados));
        }

        @ParameterizedTest
        @CsvSource({
                "null",
                "''",
                "' '"
        })
        @DisplayName("Deve lançar ExcecaoNomeInvalido para nome inválido")
        void nomeInvalidoDeveRetornarExcecao(String nome) {
            nome = validaSeNullEstaEmTexto(nome);
            DadosCliente dados = Cliente.entradaDadosCliente(nome, CPF, IDADE);
            assertThrows(ExcecaoNomeInvalido.class, () -> Cliente.criarCliente(dados));
        }
    
        @ParameterizedTest
        @ValueSource(ints = { 15, 16, 17 })
        @DisplayName("Deve lançar ExcecaoIdadeInvalida para idade inválida")
        void menorDeIdade(int idade) {
            DadosCliente dados = Cliente.entradaDadosCliente(NOME, CPF, idade);
            assertThrows(ExcecaoIdadeInvalida.class, () -> Cliente.criarCliente(dados));
        }
    
        @ParameterizedTest
        @ValueSource(ints = { 18, 19, 20 })
        @DisplayName("Deve criar cliente para idade válida")
        void maiorDeIdade(int idade) {
            DadosCliente dados = Cliente.entradaDadosCliente(NOME, CPF, idade);
            Cliente cliente = Cliente.criarCliente(dados);
            assertNotNull(cliente);
        }

        private String validaSeNullEstaEmTexto(String valor) {
            return valor.equals("null") ? null : valor;
        }

    }

    @Test
    @DisplayName("Deve criar cliente")
    void testCriarCliente() {
        DadosCliente dadosCliente = Cliente.entradaDadosCliente(NOME, CPF, IDADE);
        Cliente cliente = Cliente.criarCliente(dadosCliente);

        assertNotNull(cliente);
    }

    @Test
    @DisplayName("Deve criar dados do cliente")
    void testEntradaDadosCliente() {
        DadosCliente dadosCliente = Cliente.entradaDadosCliente(NOME, CPF, IDADE);
        assertNotNull(dadosCliente);
    }

    @Test
    @DisplayName("Deve retornar o estado do cliente em String")
    void testToString() {
        DadosCliente dadosCliente = Cliente.entradaDadosCliente(NOME, CPF, IDADE);
        Cliente cliente = Cliente.criarCliente(dadosCliente);

        assertNotNull(cliente.toString());
    }

}
