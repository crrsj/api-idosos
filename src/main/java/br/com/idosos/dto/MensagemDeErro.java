package br.com.idosos.dto;

import org.springframework.http.HttpStatus;

public record MensagemDeErro(HttpStatus Status,String mensagem) {

}
