package dev.codelevel.prog_async.dominio.cliente.modelo;

import dev.codelevel.prog_async.dominio.cliente.excecoes.ExcecaoIdadeInvalida;

public class Idade {
    
    private final int valor;

    private Idade(int valor) {
        if (valor < 18) {
            throw new ExcecaoIdadeInvalida("Menor de idade: "+valor);
        } 
        this.valor = valor;
    }

    public int valor() {
        return valor;
    }

    public static Idade criaIdade(int valor) {
        return new Idade(valor);
    }
    
}
