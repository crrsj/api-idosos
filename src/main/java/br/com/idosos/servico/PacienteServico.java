package br.com.idosos.servico;

import java.util.List;
import java.util.Optional;

import br.com.idosos.excessoes.PacienteNaoEncontrado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.idosos.modelo.Paciente;
import br.com.idosos.repositorio.PacienteRepositorio;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PacienteServico {
	
	private final PacienteRepositorio pacienteRepositorio;
	
	public Paciente cadastrarPaciente(Paciente  paciente) {

		return pacienteRepositorio.save(paciente);
		
	}
	
	public Paciente buscarPorId(Long id) {
		 return pacienteRepositorio.findById(id).orElseThrow(()->new PacienteNaoEncontrado("Paciente não encontrado"));

	}
	
	public Page<Paciente> listarPacientes(Pageable pageable){
		return  pacienteRepositorio.findAll(pageable);

	}
	
	public Paciente atualizarPaciente(Long id,Paciente paciente) {
		var buscar = pacienteRepositorio.findById(id).orElseThrow(()->new PacienteNaoEncontrado("Paciente não encontrado."));
		if(paciente.getNome() != null){
			buscar.setNome(paciente.getNome());
		}
		if (paciente.getIdade()!= null){
			buscar.setIdade(paciente.getIdade());
		}
		if(paciente.getTelefone() != null){
			buscar.setTelefone(paciente.getTelefone());
		}
		return pacienteRepositorio.save(buscar);

	}
	
	public List<Paciente>buscarPacienteNome(String nome){
		return pacienteRepositorio.findByNome(nome.trim().toUpperCase());

	}
	
	public void excluirPaciente(Long id) {
	if(!pacienteRepositorio.existsById(id)){
		throw new PacienteNaoEncontrado("Paciente não encontrado.");
	}else{
		pacienteRepositorio.deleteById(id);
	}
	}

}
