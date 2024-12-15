package br.com.idosos.controle;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.idosos.dto.PacienteDTO;
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
public class PacienteControle {
	
	private final PacienteServico pacienteServico;
	
	@PostMapping
	@Operation(summary = "Endpoint responsável por cadastrar pacientes.") 
    @ApiResponse(responseCode = "201",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<Paciente>criarPaciente(@RequestBody @Valid PacienteDTO pacienteDTO){
		var cadastrar = pacienteServico.cadastrarPaciente(pacienteDTO);
		return new ResponseEntity<>(cadastrar,HttpStatus.CREATED);
	}

	@GetMapping
	@Operation(summary = "Endpoint responsável pela busca dos pacientes.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<List<PacienteDTO>>listarPacientes(){
		var listar = pacienteServico.listarPacientes();
		return new ResponseEntity<>(listar,HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	@Operation(summary = "Endpoint responsável pela busca do paciente pelo id.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<Paciente>buscarPorId(@PathVariable Long id){
		var buscar = pacienteServico.buscarPorId(id);
		return new ResponseEntity<>(buscar, HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	@Operation(summary = "Endpoint responsável pela atualização do paciente pelo Id.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<Paciente>atualizarPaciente(@RequestBody @Valid PacienteDTO pacieDto, @PathVariable Long id){
		var atualizar = pacienteServico.atualizarPaciente(pacieDto, id);
		return new ResponseEntity<>(atualizar,HttpStatus.OK);
		
	}
	
	@GetMapping("buscarPorNome")
	@Operation(summary = "Endpoint responsável pela busca de pacientes pelo nome.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<List<Paciente>>buscarPorNomer(@PathParam("nome")String nome){		
		var buscaNome = pacienteServico.buscarPacienteNome(nome);
		return new ResponseEntity<>(buscaNome,HttpStatus.OK);
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
