package br.com.idosos.controle;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.idosos.modelo.Endereco;
import br.com.idosos.servico.EnderecoServico;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/endereco")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EnderecoControle {
	
	private final EnderecoServico enderecoServico;
	
	@PostMapping("/{pacienteId}")
	@Operation(summary = "Endpoint responsável por cadastrar endereços.")
	@ApiResponse(responseCode = "201",description = " sucesso",content = {
			@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
	})
	public ResponseEntity<Endereco>criarEndereco(@PathVariable Long pacienteId,@RequestBody @Valid Endereco endereco){

		return ResponseEntity.status(HttpStatus.CREATED).body(enderecoServico.cadastrarEndereco(pacienteId, endereco));
	}

	@PutMapping("/{id}")
	@Operation(summary = "Endpoint responsável por atualizar endereços.")
	@ApiResponse(responseCode = "200",description = " sucesso",content = {
			@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
	})
	public ResponseEntity<Endereco> atualizarEndereco(@PathVariable Long id,@RequestBody Endereco endereco){
		return ResponseEntity.status(HttpStatus.OK).body(enderecoServico.atualizarEndereco(id, endereco));
	}

	@GetMapping("/{id}")
	@Operation(summary = "Endpoint responsável por buscar endereço pelo id.")
	@ApiResponse(responseCode = "200",description = " sucesso",content = {
			@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
	})
	public ResponseEntity<Endereco>buscarPorId(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(enderecoServico.buscarPorId(id));
	}

	@GetMapping
	@Operation(summary = "Endpoint responsável por buscar enderecos.")
	@ApiResponse(responseCode = "200",description = " sucesso",content = {
			@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
	})
	public ResponseEntity<Page<Endereco>>listarEnderecos(Pageable pageable){
		Page<Endereco>enderecos=enderecoServico.listarEnderecos(pageable);
		return ResponseEntity.ok(enderecos);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void>excluirEndereco(@PathVariable Long id){
		enderecoServico.excluirEndereco(id);
		return ResponseEntity.noContent().build();
	}
}
