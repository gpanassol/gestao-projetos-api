package br.com.desbravador.services;

import br.com.desbravador.dto.MembroDTO;

import java.util.List;

public interface MembroService {

    MembroDTO save(MembroDTO projeto);

    MembroDTO update(Long id, MembroDTO projeto);

    MembroDTO findById(Long id);

    void delete(Long id);

    List<MembroDTO> findAllPessoa();
}
