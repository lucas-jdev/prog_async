package dev.codelevel.prog_async.dominio.cliente.modelo;

import dev.codelevel.prog_async.dominio.cliente.excecoes.ExcecaoCPFInvalido;

public class Cpf {
    
    private final String valor;

    private Cpf(String valor) {
        if (valor == null || valor.isEmpty() || valor.isBlank()) {
            throw new ExcecaoCPFInvalido("CPF inválido: "+valor);
        } 
            
        this.valor = valor;
    }

    public String valor() {
        return valor;
    }

    public static Cpf criaCPF(String valor) {
        return new Cpf(valor);
    }

    public void validaCpf(){
        String cpf = valor.replace(".", "");
        cpf = cpf.replace("-", "");
    
        if (cpf == null || cpf.isEmpty() || cpf.isBlank() || cpf.length() != 11) {
            throw new ExcecaoCPFInvalido("CPF inválido: "+cpf);
        }
    }

}
