package br.com.desbravador.services.impl;

import br.com.desbravador.dto.PessoaDTO;
import br.com.desbravador.entities.PessoaEntity;
import br.com.desbravador.repositories.PessoaRepository;
import br.com.desbravador.services.PessoaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PessoaRepository repository;

    @Override
    public PessoaDTO save(PessoaDTO pessoa) {
        PessoaEntity entity = convertDTOToEntity(pessoa);
        return convertEntityToDTO(repository.save(entity));
    }

    @Override
    public PessoaDTO update(Long id, PessoaDTO pessoa) {
        repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrado"));

        PessoaEntity entity = convertDTOToEntity(pessoa);
        entity.setId(id);

        return convertEntityToDTO(repository.save(entity));
    }

    @Override
    public PessoaDTO findById(Long id) {
        PessoaEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrado"));
        return convertEntityToDTO(entity);
    }

    @Override
    public void delete(Long id) {
        PessoaEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pessoa não encontrado"));
        repository.delete(entity);
    }

    @Override
    public List<PessoaDTO> findAllPessoa() {
        List<PessoaEntity> entities = repository.findAll();
        List<PessoaDTO> projetoDTOS = entities.stream().map(this::convertEntityToDTO).collect(Collectors.toList());

        return projetoDTOS;
    }

    private PessoaDTO convertEntityToDTO(PessoaEntity entity) {
        return modelMapper.map(entity, PessoaDTO.class);
    }

    private PessoaEntity convertDTOToEntity(PessoaDTO pessoa) {
        return modelMapper.map(pessoa, PessoaEntity.class);
    }
}
