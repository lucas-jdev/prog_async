package dev.codelevel.prog_async.infraestrutura.bd.mongo_reactive.repositorio;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import dev.codelevel.prog_async.infraestrutura.bd.mongo_reactive.schema.ClienteSchema;
import reactor.core.publisher.Mono;

public interface ClienteRepositorio extends ReactiveMongoRepository<ClienteSchema, ObjectId>{
    
    Mono<ClienteSchema> findByCpf(String cpf);

    Mono<Void> deleteByCpf(String cpf);
    
}