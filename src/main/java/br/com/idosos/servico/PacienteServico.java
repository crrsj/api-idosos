package br.com.idosos.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.idosos.dto.PacienteDTO;
import br.com.idosos.modelo.Paciente;
import br.com.idosos.repositorio.PacienteRepositorio;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PacienteServico {
	
	private final PacienteRepositorio pacienteRepositorio;
	
	public Paciente cadastrarPaciente(PacienteDTO  pacienteDTO) {
		var cadastrar = new Paciente(pacienteDTO);
		return pacienteRepositorio.save(cadastrar);
		
	}
	
	public Paciente buscarPorId(Long id) {
		Optional<Paciente>buscar = pacienteRepositorio.findById(id);
		return buscar.get();
	}
	
	public List<PacienteDTO>listarPacientes(){
		var listar =  pacienteRepositorio.findAll().stream().map(PacienteDTO::new).toList();
		return listar;
	}
	
	public Paciente atualizarPaciente(PacienteDTO pacienteDTO,Long id) {
		var atualizar = new Paciente(pacienteDTO);
		atualizar.setId(id);
		return pacienteRepositorio.save(atualizar);
	}
	
	public List<Paciente>buscarPacienteNome(String nome){
		var buscaNome  = pacienteRepositorio.findByNome(nome.trim().toUpperCase());
		return buscaNome;
	}
	
	public void excluirPaciente(Long id) {
		pacienteRepositorio.deleteById(id);
	}

}
