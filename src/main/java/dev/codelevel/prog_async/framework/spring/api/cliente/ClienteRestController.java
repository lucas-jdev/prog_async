package dev.codelevel.prog_async.framework.spring.api.cliente;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.codelevel.prog_async.aplicacao.caso_de_uso.cliente.BuscaClientePorCPFUseCase;
import dev.codelevel.prog_async.aplicacao.caso_de_uso.cliente.BuscaClientesUseCase;
import dev.codelevel.prog_async.aplicacao.caso_de_uso.cliente.CriaClienteUseCase;
import dev.codelevel.prog_async.aplicacao.caso_de_uso.cliente.DeletaClienteUseCase;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("v1/clientes")
public class ClienteRestController {

    @Autowired
    private BuscaClientesUseCase buscaClientes;

    @Autowired
    private DeletaClienteUseCase deletaCliente;

    @Autowired
    private BuscaClientePorCPFUseCase buscaClientePorCpf;

    @Autowired
    private CriaClienteUseCase criaCliente;

    @PostMapping
    public void criar(@RequestBody ClienteRequest clienteRequest) {
        criaCliente.executar(CriaClienteUseCase.entradaCriaCliente(clienteRequest.nome(), 
                                                                    clienteRequest.cpf(), 
                                                                    clienteRequest.idade()));
    }

    @GetMapping("/{cpf}")
    public Mono<ClienteResponse> buscarPorCpf(@PathVariable String cpf) {
        return Mono.create(emitter -> buscaClientePorCpf.executar(
                cpf, 
                callback -> emitter.success(new ClienteResponse(callback.nome(), callback.idade())), 
                emitter::error
            )
        );
    }

    @GetMapping(produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<ClienteResponse> buscarTodos() {
        Flux<ClienteResponse> flux = Flux.create(emitter -> buscaClientes.executar(
                clientes -> {
                    Flux.fromIterable(clientes)
                            .map(cliente -> new ClienteResponse(cliente.nome(), cliente.idade()))
                            .cache()
                            .delayElements(Duration.ofSeconds(2))
                            .parallel()
                            .subscribe(
                                    emitter::next,
                                    emitter::error,
                                    emitter::complete);
                },
                emitter::error
            )
        );
        return flux.log();
    }

    @DeleteMapping("/{cpf}")
    public void deletarPorCpf(@PathVariable String cpf) {
        deletaCliente.executar(DeletaClienteUseCase.dadosEntrada(cpf));
    }


    public record ClienteResponse(
        String nome, 
        int idade
    ) {
    }

    public record ClienteRequest(
        String nome, 
        int idade, 
        String cpf
    ) {
    }

}
