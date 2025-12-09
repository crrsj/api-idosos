package br.com.idosos.controle;

import java.util.List;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.idosos.modelo.Paciente;
import br.com.idosos.servico.PacienteServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/paciente")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PacienteControle {
	
	private final PacienteServico pacienteServico;
	
	@PostMapping
	@Operation(summary = "Endpoint responsável por cadastrar pacientes.") 
    @ApiResponse(responseCode = "201",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<Paciente>criarPaciente(@RequestBody @Valid Paciente paciente){
		return ResponseEntity.status(HttpStatus.CREATED).body(pacienteServico.cadastrarPaciente(paciente));
	}

	@GetMapping
	@Operation(summary = "Endpoint responsável pela busca dos pacientes.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<Page<Paciente>>listarPacientes(Pageable pageable){
	   Page<Paciente>pacientes = pacienteServico.listarPacientes(pageable);
		return ResponseEntity.status(HttpStatus.OK).body(pacientes);
	}
	
	@GetMapping("{id}")
	@Operation(summary = "Endpoint responsável pela busca do paciente pelo id.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<Paciente>buscarPorId(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(pacienteServico.buscarPorId(id));
	}
	
	@PutMapping("{id}")
	@Operation(summary = "Endpoint responsável pela atualização do paciente pelo Id.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<Paciente>atualizarPaciente(@PathVariable Long id, @RequestBody Paciente paciente){
	return ResponseEntity.status(HttpStatus.OK).body(pacienteServico.atualizarPaciente(id,paciente));
		
	}
	
	@GetMapping("buscarPorNome")
	@Operation(summary = "Endpoint responsável pela busca de pacientes pelo nome.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<List<Paciente>>buscarPorNomer(@PathParam("nome")String nome){
	return ResponseEntity.status(HttpStatus.OK).body(pacienteServico.buscarPacienteNome(nome));
	}
	
	@DeleteMapping("{id}")
	@Operation(summary = "Endpoint responsável por excluir pacientes pelo id.") 
    @ApiResponse(responseCode = "204",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public void excluirPaciente(@PathVariable Long id) {
		pacienteServico.excluirPaciente(id);
	}
}
