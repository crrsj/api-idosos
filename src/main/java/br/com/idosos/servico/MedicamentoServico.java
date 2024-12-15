package br.com.idosos.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.idosos.modelo.Medicamento;
import br.com.idosos.repositorio.MedicamentoRepositorio;
import br.com.idosos.repositorio.PacienteRepositorio;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MedicamentoServico {
	
	private final PacienteRepositorio pacienteRepositorio;
	private final MedicamentoRepositorio medicamentoRepositorio;
	
	public Medicamento cadastrarMedicamento(Medicamento medicamento,Long pacienteId) {
		var paciente = pacienteRepositorio.findById(pacienteId).get();
		medicamento.setPaciente(paciente);
		return medicamentoRepositorio.save(medicamento);
	}
	
	public List<Medicamento>listarMedicamentos(){
		return medicamentoRepositorio.findAll();
	}
	
	public Medicamento buscarPorId(Long id) {
		Optional<Medicamento>buscar = medicamentoRepositorio.findById(id);
		return buscar.get();
	}
	
	public Medicamento atualizarMedicamento(Medicamento medicamento,Long id) {
		medicamento.setId(id);
		return medicamentoRepositorio.save(medicamento);
	}
    
	public void excluirMedicamento(Long id) {
		medicamentoRepositorio.deleteById(id);
	}
}
