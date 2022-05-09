package br.com.desbravador.service;

import br.com.desbravador.dto.MembroDTO;
import br.com.desbravador.dto.PessoaDTO;
import br.com.desbravador.dto.ProjetoDTO;
import br.com.desbravador.entities.MembroEntity;
import br.com.desbravador.entities.PessoaEntity;
import br.com.desbravador.entities.ProjetoEntity;
import br.com.desbravador.enums.Status;
import br.com.desbravador.repositories.MembroRepository;
import br.com.desbravador.services.impl.MembroServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class MembroServiceTest {

    @InjectMocks
    private MembroServiceImpl service;

    @Mock
    private MembroRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void deveSalvarProjetoCorretamente() {

        ProjetoEntity projetoId = ProjetoEntity.builder()
                .id(1l)
                .nome("Projeto Teste - Teste")
                .dataInicio(LocalDateTime.now())
                .gerenteResponsavel("Gerente X")
                .previsaoTermino(LocalDateTime.now())
                .dataRealTermino(LocalDateTime.now())
                .orcamentoTotal(BigDecimal.ONE)
                .descricao("Descrição do projeto")
                .status(Status.ANALISE_APROVADA.toString())
                .build();

        PessoaEntity pessoaId = PessoaEntity.builder()
                .nome("Pessoa Teste")
                .funcionario(Boolean.TRUE)
                .build();

        MembroEntity membroEntity = MembroEntity.builder()
                .idProjeto(projetoId)
                .idPessoa(pessoaId)
                .build();

        MembroEntity membroEntityRetorno = MembroEntity.builder()
                .id(1l)
                .idProjeto(projetoId)
                .idPessoa(pessoaId)
                .build();

        ProjetoDTO projetoDTOId = ProjetoDTO.builder()
                .id(1l)
                .nome("Projeto Teste")
                .dataInicio(LocalDateTime.now())
                .gerenteResponsavel("Gerente X")
                .previsaoTermino(LocalDateTime.now())
                .dataRealTermino(LocalDateTime.now())
                .orcamentoTotal(BigDecimal.ONE)
                .descricao("Descrição do projeto")
                .status(Status.INICIADO.toString())
                .build();

        PessoaDTO pessoaDTOId = PessoaDTO.builder()
                .id(1l)
                .nome("Pessoa Teste")
                .funcionario(Boolean.TRUE)
                .build();

        MembroDTO membroDTO = MembroDTO.builder()
                .id(1l)
                .idProjeto(projetoDTOId)
                .idPessoa(pessoaDTOId)
                .build();

        when(repository.save(membroEntity)).thenReturn(membroEntityRetorno);

        when(modelMapper.map(membroDTO, MembroEntity.class)).thenReturn(membroEntity);
        when(modelMapper.map(membroEntity, MembroDTO.class)).thenReturn(membroDTO);
        when(modelMapper.map(membroEntityRetorno, MembroDTO.class)).thenReturn(membroDTO);


        MembroDTO projetoDTO1 = service.save(membroDTO);

        assertThat(projetoDTO1.getId()).isNotNull();
    }
}
