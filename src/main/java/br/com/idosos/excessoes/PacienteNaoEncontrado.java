package br.com.idosos.excessoes;

public class PacienteNaoEncontrado extends RuntimeException {
    public PacienteNaoEncontrado(String mensagem) {
        super(mensagem);
    }
    public PacienteNaoEncontrado(){
        super();
    }
}
