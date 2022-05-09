package br.com.desbravador.services;

import br.com.desbravador.dto.ProjetoDTO;

import java.util.List;

public interface ProjetoService {

    ProjetoDTO save(ProjetoDTO projeto);

    ProjetoDTO update(Long id, ProjetoDTO projeto);

    ProjetoDTO findById(Long id);

    void delete(Long id);

    List<ProjetoDTO> findAllProjeto();

}
