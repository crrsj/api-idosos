package br.com.idosos.dto;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import br.com.idosos.modelo.Endereco;
import br.com.idosos.modelo.Medicamento;
import br.com.idosos.modelo.Paciente;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PacienteDTO {
	
	private Long id;
	private String dataCadastro = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
	@NotBlank(message = "não pode estar em branco !")
	private String nome;
	private String telefone;
	private Integer idade;
	private List<Endereco>endereco;
	private List< Medicamento> medicamento;
	
	public PacienteDTO(Paciente paciente) {
		this.id = paciente.getId();
		this.nome = paciente.getNome();
		this.telefone = paciente.getTelefone();
		this.idade = paciente.getIdade();
		this.endereco = paciente.getEndereco();
		this.medicamento = paciente.getMedicamento();
		
		
	}

}
