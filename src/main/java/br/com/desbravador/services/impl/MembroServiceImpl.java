package br.com.desbravador.services.impl;

import br.com.desbravador.dto.MembroDTO;
import br.com.desbravador.entities.MembroEntity;
import br.com.desbravador.repositories.MembroRepository;
import br.com.desbravador.services.MembroService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

public class MembroServiceImpl implements MembroService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MembroRepository repository;

    @Override
    public MembroDTO save(MembroDTO membro) {
        MembroEntity entity = convertDTOToEntity(membro);
        return convertEntityToDTO(repository.save(entity));
    }

    @Override
    public MembroDTO update(Long id, MembroDTO membro) {
        repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Membro não encontrado"));

        MembroEntity entity = convertDTOToEntity(membro);
        entity.setId(id);

        return convertEntityToDTO(repository.save(entity));
    }

    @Override
    public MembroDTO findById(Long id) {
        MembroEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Membro não encontrado"));
        return convertEntityToDTO(entity);
    }

    @Override
    public void delete(Long id) {
        MembroEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Membro não encontrado"));
        repository.delete(entity);
    }

    @Override
    public List<MembroDTO> findAllPessoa() {
        List<MembroEntity> entities = repository.findAll();
        List<MembroDTO> membroDTOS = entities.stream().map(this::convertEntityToDTO).collect(Collectors.toList());

        return membroDTOS;
    }

    private MembroDTO convertEntityToDTO(MembroEntity entity) {
        return modelMapper.map(entity, MembroDTO.class);
    }

    private MembroEntity convertDTOToEntity(MembroDTO pessoa) {
        return modelMapper.map(pessoa, MembroEntity.class);
    }
}
