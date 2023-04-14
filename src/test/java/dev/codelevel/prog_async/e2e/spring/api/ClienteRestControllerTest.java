package dev.codelevel.prog_async.e2e.spring.api;

import java.time.Duration;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;

import dev.codelevel.prog_async.framework.spring.SpringRunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = SpringRunner.class)
@AutoConfigureWebTestClient
class ClienteRestControllerTest {

    @Test
    @Order(4)
    void buscarClientes(@Autowired WebTestClient webClient) {
        webClient
            .get().uri("/v1/clientes")
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus().isOk();
    }

    @Test
    @Order(2)
    void buscarClientePorCpf(@Autowired WebTestClient webClient) {
        webClient = webClient.mutate().responseTimeout(Duration.ofSeconds(10)).build();
        webClient
            .get().uri("/v1/clientes/515.122.123-12")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk();
    }

    @Test
    @Order(1)
    void criarCliente(@Autowired WebTestClient webClient) {
        webClient
            .post().uri("/v1/clientes")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue("""
                {
                    "nome": "João",
                    "cpf": "515.122.123-12", 
                    "idade": 20
                }""")
            .exchange()
            .expectStatus().isOk();
    }

    @Test
    @Order(3)
    void deletarCliente(@Autowired WebTestClient webClient) {
        webClient
            .delete().uri("/v1/clientes/515.122.123-12")
            .exchange()
            .expectStatus().isOk();
    }

}
