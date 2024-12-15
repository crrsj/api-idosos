package br.com.idosos.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.idosos.enums.Via;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor
@Data
public class Medicamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	@Enumerated(EnumType.STRING)
	private Via via;
	private String DescricaoDosagem;
	private String enfermidade;
	private String horarios;
	private String obs;
	@ManyToOne
	@JoinColumn(name = "idPaciente")
	@JsonIgnore
	private Paciente paciente;


}
