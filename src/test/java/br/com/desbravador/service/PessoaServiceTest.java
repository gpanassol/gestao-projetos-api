package br.com.desbravador.service;

import br.com.desbravador.dto.PessoaDTO;
import br.com.desbravador.entities.PessoaEntity;
import br.com.desbravador.repositories.PessoaRepository;
import br.com.desbravador.services.impl.PessoaServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class PessoaServiceTest {

    @InjectMocks
    private PessoaServiceImpl service;

    @Mock
    private PessoaRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void deveSalvarPessoaCorretamente() {

        PessoaEntity pessoaEntity = PessoaEntity.builder()
                .nome("Pessoa Teste")
                .funcionario(Boolean.TRUE)
                .build();

        PessoaEntity pessoaEntityRetorno = PessoaEntity.builder()
                .id(1l)
                .nome("Pessoa Teste")
                .funcionario(Boolean.TRUE)
                .build();

        PessoaDTO pessoaDTO = PessoaDTO.builder()
                .nome("Pessoa Teste")
                .funcionario(Boolean.TRUE)
                .build();

        PessoaDTO pessoaDTORetorno = PessoaDTO.builder()
                .id(1l)
                .nome("Pessoa Teste")
                .funcionario(Boolean.TRUE)
                .build();

        when(repository.save(pessoaEntity)).thenReturn(pessoaEntityRetorno);
        when(modelMapper.map(pessoaDTO, PessoaEntity.class)).thenReturn(pessoaEntity);

        when(modelMapper.map(pessoaDTORetorno, PessoaEntity.class)).thenReturn(pessoaEntityRetorno);
        when(modelMapper.map(pessoaEntityRetorno, PessoaDTO.class)).thenReturn(pessoaDTORetorno);

        PessoaDTO projetoDTO1 = service.save(pessoaDTO);

        assertThat(projetoDTO1.getId()).isNotNull();
    }

    @Test
    public void deveAtualizarPessoaCorretamente() {

        PessoaEntity pessoaEntity = PessoaEntity.builder()
                .id(1l)
                .nome("Pessoa Teste - Update")
                .funcionario(Boolean.TRUE)
                .build();

        Optional<PessoaEntity> optionalPessoaEntity = Optional.of(pessoaEntity);

        PessoaDTO pessoaDTO = PessoaDTO.builder()
                .id(1l)
                .nome("Pessoa Teste")
                .funcionario(Boolean.TRUE)
                .build();

        when(repository.findById(pessoaDTO.getId())).thenReturn(optionalPessoaEntity);
        when(repository.save(pessoaEntity)).thenReturn(pessoaEntity);
        when(modelMapper.map(pessoaDTO, PessoaEntity.class)).thenReturn(pessoaEntity);
        when(modelMapper.map(pessoaEntity, PessoaDTO.class)).thenReturn(pessoaDTO);

        PessoaDTO pessoaDTO1 = service.update(pessoaDTO.getId(), pessoaDTO);

        assertThat(pessoaDTO1.getId()).isNotNull();
    }

    @Test
    public void deveDeletarPessoaCorretamente() {

        PessoaEntity pessoaEntity = PessoaEntity.builder()
                .id(1l)
                .nome("Pessoa Teste - Update")
                .funcionario(Boolean.TRUE)
                .build();

        Optional<PessoaEntity> optionalPessoaEntity = Optional.of(pessoaEntity);

        PessoaDTO pessoaDTO = PessoaDTO.builder()
                .id(1l)
                .nome("Pessoa Teste")
                .funcionario(Boolean.TRUE)
                .build();

        when(repository.findById(pessoaDTO.getId())).thenReturn(optionalPessoaEntity);

        service.delete(pessoaDTO.getId());
    }
}
