package dev.codelevel.prog_async.dominio.cliente.excecoes;

public class ExcecaoCPFInvalido extends RuntimeException{

    private final static String MENSAGEM = "CPF inválido";

    public ExcecaoCPFInvalido() {
        super(MENSAGEM);
    }

    public ExcecaoCPFInvalido(String mensagem) {
        super(mensagem);
    }

}