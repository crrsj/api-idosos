package br.com.idosos.servico;

import java.util.List;
import java.util.Optional;

import br.com.idosos.excessoes.MedicamentoNaoEncontrado;
import br.com.idosos.excessoes.PacienteNaoEncontrado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	
	public Medicamento cadastrarMedicamento(Long pacienteId, Medicamento medicamento) {
		var paciente = pacienteRepositorio.findById(pacienteId).orElseThrow(PacienteNaoEncontrado::new);
		medicamento.setPaciente(paciente);
		return medicamentoRepositorio.save(medicamento);
	}
	
	public Page<Medicamento> listarMedicamentos(Pageable pageable){
		return medicamentoRepositorio.findAll(pageable);
	}
	
	public Medicamento buscarPorId(Long id) {
	return  medicamentoRepositorio.findById(id).orElseThrow(()-> new MedicamentoNaoEncontrado("Medicamento não encontrado."));

	}
	
	public Medicamento atualizarMedicamento(Long id, Medicamento medicamento) {
		var buscarMed =  medicamentoRepositorio.findById(id).orElseThrow(PacienteNaoEncontrado::new);
		if(medicamento.getNomeMedicamento() != null){
			buscarMed.setNomeMedicamento(medicamento.getNomeMedicamento());
		}
        if(medicamento.getVia() != null){
			buscarMed.setVia(medicamento.getVia());
		}
		if (medicamento.getEnfermidade() != null){
			buscarMed.setEnfermidade(medicamento.getEnfermidade());
		}
		if(medicamento.getDescricao() != null){
			buscarMed.setDescricao(medicamento.getDescricao());
		}

		return medicamentoRepositorio.save(buscarMed);


	}
    
	public void excluirMedicamento(Long id) {

		if(!medicamentoRepositorio.existsById(id)){
			throw new MedicamentoNaoEncontrado();
		}else{
			medicamentoRepositorio.deleteById(id);
		}
	}
}
