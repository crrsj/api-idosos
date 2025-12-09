package br.com.idosos.controle;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
@CrossOrigin(origins = "*")
public class MedicamentoControle {
	
	private final MedicamentoServico medicamentoServico;
	
	@PostMapping("{pacienteId}")
	@Operation(summary = "Endpoint responsável por cadastrar medicamentos.") 
    @ApiResponse(responseCode = "201",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<Medicamento>cadastrarMedicameto(@PathVariable Long pacienteId,@RequestBody Medicamento medicamento){
		return ResponseEntity.status(HttpStatus.CREATED).body(medicamentoServico.cadastrarMedicamento(pacienteId, medicamento));
	}

	@GetMapping
	@Operation(summary = "Endpoint responsável por buscar medicamentos.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<Page<Medicamento>>listarMedicamentos(Pageable pageable){
		Page<Medicamento>medicamentos = medicamentoServico.listarMedicamentos(pageable);
		return ResponseEntity.status(HttpStatus.OK).body(medicamentos);
	}
	
	@GetMapping("{id}")
	@Operation(summary = "Endpoint responsável por buscar medicamento pelo id.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<Medicamento>buscarPorId(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(medicamentoServico.buscarPorId(id));
	}
	
	@PutMapping("{id}")
	@Operation(summary = "Endpoint responsável por atualizar medicamento pelo id.") 
    @ApiResponse(responseCode = "200",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<Medicamento>atualizarMeedicamento(@PathVariable Long id,@RequestBody Medicamento medicamento){
		return ResponseEntity.status(HttpStatus.OK).body(medicamentoServico.atualizarMedicamento(id, medicamento));
	}
	
	@DeleteMapping("{id}")
	@Operation(summary = "Endpoint responsável por excluir medicamentos.") 
    @ApiResponse(responseCode = "204",description = " sucesso",content = {
   	@Content(mediaType = "application.json",schema = @Schema(implementation = ResponseEntity.class))
    })           
	public ResponseEntity<Void>excluir(@PathVariable Long id){
		medicamentoServico.excluirMedicamento(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
