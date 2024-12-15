package br.com.idosos.controle;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.idosos.modelo.Endereco;
import br.com.idosos.servico.EnderecoServico;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/endereco")
@RequiredArgsConstructor
public class EnderecoControle {
	
	private final EnderecoServico enderecoServico;
	
	@PostMapping("{pacienteId}")
	public ResponseEntity<Endereco>criarEndereco(@RequestBody Endereco endereco,@PathVariable("pacienteId") Long pacienteId){
		var cadastro = enderecoServico.cadastrarEndereco(endereco, pacienteId);
		return new ResponseEntity<>(cadastro,HttpStatus.CREATED);
	}
	
}
