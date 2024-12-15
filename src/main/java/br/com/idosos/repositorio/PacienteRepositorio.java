package br.com.idosos.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.idosos.modelo.Paciente;

public interface PacienteRepositorio extends JpaRepository<Paciente, Long> {

	@Query(value = "select p from Paciente p where upper(trim(p.nome)) like %?1% ") 
	List<Paciente> findByNome(String nome);

}
