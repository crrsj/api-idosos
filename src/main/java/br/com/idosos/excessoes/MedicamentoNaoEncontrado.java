package br.com.idosos.excessoes;

public class MedicamentoNaoEncontrado extends RuntimeException {
    public MedicamentoNaoEncontrado(String mensagem) {
        super(mensagem);
    }

    public MedicamentoNaoEncontrado(){
        super();
    }
}
