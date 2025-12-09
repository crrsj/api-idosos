package br.com.idosos.servico;

import br.com.idosos.excessoes.EnderecoNaoEncontrado;
import br.com.idosos.excessoes.PacienteNaoEncontrado;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.idosos.modelo.Endereco;
import br.com.idosos.repositorio.EnderecoRepositorio;
import br.com.idosos.repositorio.PacienteRepositorio;
import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class EnderecoServico {

	private final PacienteRepositorio pacienteRepositorio;
	private final EnderecoRepositorio enderecoRepositorio;
			
	public Endereco cadastrarEndereco(Long pacienteId, Endereco endereco) {
		var paciente = pacienteRepositorio.findById(pacienteId).orElseThrow(PacienteNaoEncontrado::new);

		endereco.setPaciente(paciente);
	    return enderecoRepositorio.save(endereco);
	}
	
	public Endereco atualizarEndereco(Long id, Endereco endereco){
		var buscarEndereco = enderecoRepositorio.findById(id).orElseThrow(EnderecoNaoEncontrado::new);
		if (endereco.getRua()!= null){
			buscarEndereco.setRua(endereco.getRua());
		}
		if(endereco.getNumero()!= null){
			buscarEndereco.setNumero(endereco.getNumero());
		}
		if(endereco.getBairro()!= null){
			buscarEndereco.setBairro(endereco.getBairro());
		}
		if(endereco.getCidade() != null){
			buscarEndereco.setCidade(endereco.getCidade());
		}

		if(endereco.getEstado() != null){
			buscarEndereco.setEstado(endereco.getEstado());
		}

		return enderecoRepositorio.save(buscarEndereco);

	}

	public Endereco buscarPorId(Long id){
		return enderecoRepositorio.findById(id).orElseThrow(EnderecoNaoEncontrado::new);

	}

	public Page<Endereco>listarEnderecos(Pageable pageable){
		return enderecoRepositorio.findAll(pageable);
	}

	public void excluirEndereco(Long id){
		if(!enderecoRepositorio.existsById(id)){
			throw new EnderecoNaoEncontrado("Endereço não encontrado.");
		}else{
			enderecoRepositorio.deleteById(id);
		}
	}
			
}
