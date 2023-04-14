package dev.codelevel.prog_async.dominio.cliente.excecoes;

public class ExcecaoIdadeInvalida extends RuntimeException {
    
    private final static String MENSAGEM = "Menor de idade";
    
    public ExcecaoIdadeInvalida() {
        super(MENSAGEM);
    }

    public ExcecaoIdadeInvalida(String mensagem) {
        super(mensagem);
    }

    public ExcecaoIdadeInvalida(int idade) {
        super(MENSAGEM+": "+idade);
    }
    
}
