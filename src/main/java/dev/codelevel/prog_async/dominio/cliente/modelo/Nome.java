package dev.codelevel.prog_async.dominio.cliente.modelo;

import dev.codelevel.prog_async.dominio.cliente.excecoes.ExcecaoNomeInvalido;

public class Nome {
 
    private final String valor;

    private Nome(String valor) {
        if (valor == null || valor.isEmpty() || valor.isBlank()) {
            throw new ExcecaoNomeInvalido("Nome inválido: "+valor);
        } 
            
        this.valor = valor;
    }

    public String valor() {
        return valor;
    }

    public static Nome criaNome(String valor) {
        return new Nome(valor);
    }

}
