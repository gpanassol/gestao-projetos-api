package br.com.desbravador.services.impl;

import br.com.desbravador.dto.ProjetoDTO;
import br.com.desbravador.entities.ProjetoEntity;
import br.com.desbravador.enums.Status;
import br.com.desbravador.exception.ProjetoExclusionException;
import br.com.desbravador.repositories.ProjetoRepository;
import br.com.desbravador.services.ProjetoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjetoServiceImpl implements ProjetoService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ProjetoRepository repository;

    @Override
    public ProjetoDTO save(ProjetoDTO projeto) {
        ProjetoEntity entity = convertDTOToEntity(projeto);
        return convertEntityToDTO(repository.save(entity));
    }

    @Override
    public ProjetoDTO update(Long id, ProjetoDTO projeto) {

        repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Projeto não encontrado"));

        ProjetoEntity entity = convertDTOToEntity(projeto);
        entity.setId(id);

        return convertEntityToDTO(repository.save(entity));
    }

    @Override
    public ProjetoDTO findById(Long id) {
        ProjetoEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Projeto não encontrado"));
        return convertEntityToDTO(entity);
    }

    @Override
    public void delete(Long id) {
        ProjetoEntity entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Projeto não encontrado"));

        if (entity.getStatus().equals(Status.INICIADO.toString())
                || entity.getStatus().equals(Status.EM_ANDAMENTO.toString())
                    || entity.getStatus().equals(Status.ENCERRADO.toString()))  {
            throw new ProjetoExclusionException("Projeto não pode ser excluido, status INICIADO, " +
                    "EM_ANDAMENTO e ENCERRADO não é possivel a exclusao");
        }

        repository.delete(entity);
    }

    @Override
    public List<ProjetoDTO> findAllProjeto() {

        List<ProjetoEntity> entities = repository.findAll();
        List<ProjetoDTO> projetoDTOS = entities.stream().map(this::convertEntityToDTO).collect(Collectors.toList());

        return projetoDTOS;
    }

    private ProjetoDTO convertEntityToDTO(ProjetoEntity entity) {
        return modelMapper.map(entity, ProjetoDTO.class);
    }

    private ProjetoEntity convertDTOToEntity(ProjetoDTO projeto) {
        return modelMapper.map(projeto, ProjetoEntity.class);
    }
}
