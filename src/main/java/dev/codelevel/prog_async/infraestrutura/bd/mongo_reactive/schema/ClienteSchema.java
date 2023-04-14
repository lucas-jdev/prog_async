package dev.codelevel.prog_async.infraestrutura.bd.mongo_reactive.schema;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "cliente")
public class ClienteSchema {
    
    @Id
    private ObjectId id;

    @Field("nome")
    private String nome;

    @Field("cpf")
    private String cpf;

    @Field("idade")
    private int idade;

    public ClienteSchema(String nome, String cpf, int idade) {
        this.id = new ObjectId();
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
    }

    public String getId(){
        return id.toHexString();
    }

}
