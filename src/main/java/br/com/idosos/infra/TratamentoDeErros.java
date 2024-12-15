package br.com.idosos.infra;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.idosos.dto.MensagemDeErro;
import br.com.idosos.dto.ValidandoCampos;

@ControllerAdvice
public class TratamentoDeErros {
	
	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<MensagemDeErro> objetoNaoEncontrado(){
		var erros = new MensagemDeErro(HttpStatus.NOT_FOUND, "Objeto não encontrado !");
		return new ResponseEntity<>(erros,HttpStatus.NOT_FOUND);
		
	}
	
	
    @ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?>ValidarCampos(MethodArgumentNotValidException ex){
		var erro = ex.getFieldErrors();
		return ResponseEntity.badRequest().body(erro.stream().map(ValidandoCampos::new).toList());
	}

}
