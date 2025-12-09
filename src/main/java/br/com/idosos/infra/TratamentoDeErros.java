package br.com.idosos.infra;

import java.util.NoSuchElementException;

import br.com.idosos.excessoes.EnderecoNaoEncontrado;
import br.com.idosos.excessoes.MedicamentoNaoEncontrado;
import br.com.idosos.excessoes.PacienteNaoEncontrado;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.idosos.dto.MensagemDeErro;
import br.com.idosos.dto.ValidandoCampos;

@ControllerAdvice
public class TratamentoDeErros {
	
	@ExceptionHandler(PacienteNaoEncontrado.class)
	public ResponseEntity<MensagemDeErro> pacienteNaoEncontrado(){
		var erros = new MensagemDeErro(HttpStatus.NOT_FOUND, "Paciente não encontrado !");
		return new ResponseEntity<>(erros,HttpStatus.NOT_FOUND);
		
	}

	@ExceptionHandler(EnderecoNaoEncontrado.class)
	public ResponseEntity<MensagemDeErro> enderecoNaoEncontrado(){
		var erros = new MensagemDeErro(HttpStatus.NOT_FOUND, "Endereço não encontrado !");
		return new ResponseEntity<>(erros,HttpStatus.NOT_FOUND);

	}

	@ExceptionHandler(MedicamentoNaoEncontrado.class)
	public ResponseEntity<MensagemDeErro> medicamentoNaoEncontrado(){
		var erros = new MensagemDeErro(HttpStatus.NOT_FOUND, "Medicamento não encontrado !");
		return new ResponseEntity<>(erros,HttpStatus.NOT_FOUND);

	}
	
	
    @ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?>ValidarCampos(MethodArgumentNotValidException ex){
		var erro = ex.getFieldErrors();
		return ResponseEntity.badRequest().body(erro.stream().map(ValidandoCampos::new).toList());
	}

}
