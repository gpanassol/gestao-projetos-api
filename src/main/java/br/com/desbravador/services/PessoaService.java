package br.com.desbravador.services;

import br.com.desbravador.dto.PessoaDTO;

import java.util.List;

public interface PessoaService {

    PessoaDTO save(PessoaDTO projeto);

    PessoaDTO update(Long id, PessoaDTO projeto);

    PessoaDTO findById(Long id);

    void delete(Long id);

    List<PessoaDTO> findAllPessoa();
}
