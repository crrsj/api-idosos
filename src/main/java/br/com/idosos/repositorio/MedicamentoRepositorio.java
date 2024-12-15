package br.com.idosos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.idosos.modelo.Medicamento;

public interface MedicamentoRepositorio extends JpaRepository<Medicamento,Long> {

}
