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

import br.com.idosos.modelo.Medicamento;
import br.com.idosos.servico.MedicamentoServico;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/medicamento")
@RequiredArgsConstructor
public class MedicamentoControle {
	
	private final MedicamentoServico medicamentoServico;
	
	@PostMapping("{pacienteId}")
	@Operation(summary = "Endpoint responsável por cadastrar medicamentos.") 
    @ApiResponse(responseCode = "201",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<Medicamento>cadastrarMedicameto(@RequestBody Medicamento medicamento,
			@PathVariable("pacienteId") Long pacienteId){
		var cadastrar = medicamentoServico.cadastrarMedicamento(medicamento, pacienteId);
		return new ResponseEntity<>(cadastrar,HttpStatus.CREATED);
	}

	@GetMapping
	@Operation(summary = "Endpoint responsável por buscar medicamentos.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<List<Medicamento>>listarMedicamentos(){
		var listar = medicamentoServico.listarMedicamentos();
		return new ResponseEntity<>(listar,HttpStatus.OK);
	}
	
	@GetMapping("{id}")
	@Operation(summary = "Endpoint responsável por buscar medicamento pelo id.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<Medicamento>buscarPorId(@PathVariable Long id){
		var buscar = medicamentoServico.buscarPorId(id);
		return new ResponseEntity<>(buscar,HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	@Operation(summary = "Endpoint responsável por atualizar medicamento pelo id.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<Medicamento>atualizarMeedicamento(@RequestBody Medicamento medicamento,@PathVariable Long id){
		var med = medicamentoServico.atualizarMedicamento(medicamento, id);
		return new ResponseEntity<>(med,HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	@Operation(summary = "Endpoint responsável por excluir medicamentos.") 
    @ApiResponse(responseCode = "204",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<Void>excluir(@PathVariable Long id){
		medicamentoServico.excluirMedicamento(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
