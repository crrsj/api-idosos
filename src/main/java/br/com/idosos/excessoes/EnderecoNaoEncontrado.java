package br.com.idosos.excessoes;

public class EnderecoNaoEncontrado extends RuntimeException {
    public EnderecoNaoEncontrado(String mensagem) {
        super(mensagem);
    }
    public EnderecoNaoEncontrado(){

    }
}
