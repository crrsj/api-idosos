package br.com.idosos.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
	@NotBlank(message = "não pode estar em branco !")
	private String nome;
	@NotBlank(message = "não pode estar em branco !")
	private String telefone;
	@NotNull(message = "não pode ser nulo!")
	private Integer idade;
	@OneToMany(mappedBy = "paciente",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Medicamento>medicamento;
	@OneToMany(mappedBy = "paciente",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<Endereco>endereco;



}
