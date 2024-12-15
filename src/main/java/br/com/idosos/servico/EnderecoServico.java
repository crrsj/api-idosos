package br.com.idosos.servico;

import org.springframework.stereotype.Service;

import br.com.idosos.modelo.Endereco;
import br.com.idosos.repositorio.EnderecoRepositorio;
import br.com.idosos.repositorio.PacienteRepositorio;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnderecoServico {

	private final PacienteRepositorio pacienteRepositorio;
	private final EnderecoRepositorio enderecoRepositorio;
			
	public Endereco cadastrarEndereco(Endereco endereco,Long pacienteId) {
		var paciente = pacienteRepositorio.findById(pacienteId).get();
	    endereco.setPaciente(paciente);
	    return enderecoRepositorio.save(endereco);
	}
	
	
			
}
