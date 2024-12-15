package br.com.idosos.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.idosos.dto.PacienteDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name= "tb_pacientes")
@Data
@NoArgsConstructor
public class Paciente {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String dataCadastro = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
	private String nome;
	private String telefone;
	private Integer idade;
	@OneToMany(mappedBy = "paciente",cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonIgnore
	private List<Medicamento>medicamento;
	@OneToMany(mappedBy = "paciente",cascade = CascadeType.ALL,orphanRemoval = true)
	@JsonIgnore
	private List<Endereco>endereco;

	public Paciente(PacienteDTO pacienteDTO) {
		this.id = pacienteDTO.getId();
		this.dataCadastro = pacienteDTO.getDataCadastro();
		this.nome = pacienteDTO.getNome();
		this.telefone = pacienteDTO.getTelefone();
		this.idade = pacienteDTO.getIdade();
	}

}
