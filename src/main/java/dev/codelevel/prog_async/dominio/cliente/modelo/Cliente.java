package dev.codelevel.prog_async.dominio.cliente.modelo;

import java.util.Objects;

public class Cliente {
    
    private final Nome nome;
    private final Cpf cpf;
    private final Idade idade;

    private Cliente(Nome nome, Cpf cpf, Idade idade) {
        this.nome = nome;
        this.cpf = cpf;
        this.idade = idade;
    }

    public String nome() {
        return nome.valor();
    }

    public String cpf() {
        return cpf.valor();
    }

    public int idade() {
        return idade.valor();
    }

    private Cliente() {
        this(null, null, null);
    }

    public static Cliente criarCliente(DadosCliente dados) {
        return new Cliente(Nome.criaNome(dados.nome), 
                            Cpf.criaCPF(dados.cpf), 
                            Idade.criaIdade(dados.idade));
    }

    public static DadosCliente entradaDadosCliente(String nome, String cpf, int idade) {
        return new DadosCliente(nome, cpf, idade);
    }

    public String toString() {
        return "Cliente [cpf=" + cpf.valor() + ", idade=" + idade.valor() + ", nome=" + nome.valor() + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((cpf == null) ? 0 : cpf.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Cliente other = (Cliente) obj;
        return Objects.equals(cpf, other.cpf);
    }

    public record DadosCliente(String nome, String cpf, int idade) {
    }

}
