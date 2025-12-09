package br.com.idosos.modelo;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.idosos.enums.Via;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.loadtime.definition.Definition;

@Entity
@Table
@NoArgsConstructor
@Data
public class Medicamento {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nomeMedicamento;
	@Enumerated(EnumType.STRING)
	private Via via;
	private String enfermidade;
	@Column(columnDefinition = "TEXT")
	private String Descricao;
	@ManyToOne
	@JoinColumn(name = "pacienteId")
	@JsonIgnoreProperties({"medicamento", "endereco"})
	private Paciente paciente;


}
