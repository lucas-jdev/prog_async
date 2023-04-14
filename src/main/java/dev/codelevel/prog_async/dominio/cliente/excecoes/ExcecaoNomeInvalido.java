package dev.codelevel.prog_async.dominio.cliente.excecoes;

public class ExcecaoNomeInvalido extends RuntimeException{
    
    private final static String MENSAGEM = "Nome inválido";

    public ExcecaoNomeInvalido() {
        super(MENSAGEM);
    }

    public ExcecaoNomeInvalido(String mensagem) {
        super(mensagem);
    }

}
