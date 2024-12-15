package br.com.idosos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.idosos.modelo.Endereco;

public interface EnderecoRepositorio extends JpaRepository<Endereco, Long> {

}
