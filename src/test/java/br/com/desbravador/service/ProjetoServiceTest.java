package br.com.desbravador.service;

import br.com.desbravador.dto.ProjetoDTO;
import br.com.desbravador.entities.ProjetoEntity;
import br.com.desbravador.enums.Status;
import br.com.desbravador.exception.ProjetoExclusionException;
import br.com.desbravador.repositories.ProjetoRepository;
import br.com.desbravador.services.impl.ProjetoServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class ProjetoServiceTest {

    @InjectMocks
    private ProjetoServiceImpl service;

    @Mock
    private ProjetoRepository repository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    public void deveSalvarProjetoCorretamente() {

        ProjetoEntity projetoEntity = ProjetoEntity.builder()
                .nome("Projeto Teste")
                .dataInicio(LocalDateTime.now())
                .gerenteResponsavel("Gerente X")
                .previsaoTermino(LocalDateTime.now())
                .dataRealTermino(LocalDateTime.now())
                .orcamentoTotal(BigDecimal.ONE)
                .descricao("Descrição do projeto")
                .status(Status.INICIADO.toString())
                .build();

        ProjetoEntity projetoEntityRetorno = ProjetoEntity.builder()
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

        ProjetoDTO projetoDTO = ProjetoDTO.builder()
                .nome("Projeto Teste")
                .dataInicio(LocalDateTime.now())
                .gerenteResponsavel("Gerente X")
                .previsaoTermino(LocalDateTime.now())
                .dataRealTermino(LocalDateTime.now())
                .orcamentoTotal(BigDecimal.ONE)
                .descricao("Descrição do projeto")
                .status(Status.INICIADO.toString())
                .build();

        ProjetoDTO projetoDTORetorno = ProjetoDTO.builder()
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

        when(repository.save(projetoEntity)).thenReturn(projetoEntityRetorno);
        when(modelMapper.map(projetoDTO, ProjetoEntity.class)).thenReturn(projetoEntity);

        when(modelMapper.map(projetoDTORetorno, ProjetoEntity.class)).thenReturn(projetoEntityRetorno);
        when(modelMapper.map(projetoEntityRetorno, ProjetoDTO.class)).thenReturn(projetoDTORetorno);

        ProjetoDTO projetoDTO1 = service.save(projetoDTO);

        assertThat(projetoDTO1.getId()).isNotNull();
    }

    @Test
    public void deveAtualizarProjetoCorretamente() {

        ProjetoEntity projetoEntity = ProjetoEntity.builder()
                .id(1l)
                .nome("Projeto Teste - Teste")
                .dataInicio(LocalDateTime.now())
                .gerenteResponsavel("Gerente X")
                .previsaoTermino(LocalDateTime.now())
                .dataRealTermino(LocalDateTime.now())
                .orcamentoTotal(BigDecimal.ONE)
                .descricao("Descrição do projeto")
                .status(Status.INICIADO.toString())
                .build();

        Optional<ProjetoEntity> optionalProjetoEntity = Optional.of(projetoEntity);

        ProjetoDTO projetoDTO = ProjetoDTO.builder()
                .id(1l)
                .nome("Projeto Teste - Teste")
                .dataInicio(LocalDateTime.now())
                .gerenteResponsavel("Gerente X")
                .previsaoTermino(LocalDateTime.now())
                .dataRealTermino(LocalDateTime.now())
                .orcamentoTotal(BigDecimal.ONE)
                .descricao("Descrição do projeto")
                .status(Status.INICIADO.toString())
                .build();

        when(repository.findById(projetoDTO.getId())).thenReturn(optionalProjetoEntity);
        when(repository.save(projetoEntity)).thenReturn(projetoEntity);
        when(modelMapper.map(projetoDTO, ProjetoEntity.class)).thenReturn(projetoEntity);
        when(modelMapper.map(projetoEntity, ProjetoDTO.class)).thenReturn(projetoDTO);

        ProjetoDTO projetoDTO1 = service.update(projetoDTO.getId(), projetoDTO);

        assertThat(projetoDTO1.getId()).isNotNull();
    }

    @Test
    public void deveDeletarProjetoCorretamente() {

        ProjetoEntity projetoEntity = ProjetoEntity.builder()
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

        Optional<ProjetoEntity> optionalProjetoEntity = Optional.of(projetoEntity);

        ProjetoDTO projetoDTO = ProjetoDTO.builder()
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

        when(repository.findById(projetoDTO.getId())).thenReturn(optionalProjetoEntity);

        service.delete(projetoDTO.getId());
    }

    @Test
    public void naoDeveDeletarProjetoIniciado() {

        ProjetoEntity projetoEntity = ProjetoEntity.builder()
                .id(1l)
                .nome("Projeto Teste - Teste")
                .dataInicio(LocalDateTime.now())
                .gerenteResponsavel("Gerente X")
                .previsaoTermino(LocalDateTime.now())
                .dataRealTermino(LocalDateTime.now())
                .orcamentoTotal(BigDecimal.ONE)
                .descricao("Descrição do projeto")
                .status(Status.INICIADO.toString())
                .build();

        Optional<ProjetoEntity> optionalProjetoEntity = Optional.of(projetoEntity);

        ProjetoDTO projetoDTO = ProjetoDTO.builder()
                .id(1l)
                .nome("Projeto Teste - Teste")
                .dataInicio(LocalDateTime.now())
                .gerenteResponsavel("Gerente X")
                .previsaoTermino(LocalDateTime.now())
                .dataRealTermino(LocalDateTime.now())
                .orcamentoTotal(BigDecimal.ONE)
                .descricao("Descrição do projeto")
                .status(Status.INICIADO.toString())
                .build();

        when(repository.findById(projetoDTO.getId())).thenReturn(optionalProjetoEntity);

        org.junit.jupiter.api.Assertions.assertThrows(ProjetoExclusionException.class, () -> {
            service.delete(projetoDTO.getId());
        });

    }

    @Test
    public void naoDeveDeletarProjetoEmAndamento() {

        ProjetoEntity projetoEntity = ProjetoEntity.builder()
                .id(1l)
                .nome("Projeto Teste - Teste")
                .dataInicio(LocalDateTime.now())
                .gerenteResponsavel("Gerente X")
                .previsaoTermino(LocalDateTime.now())
                .dataRealTermino(LocalDateTime.now())
                .orcamentoTotal(BigDecimal.ONE)
                .descricao("Descrição do projeto")
                .status(Status.EM_ANDAMENTO.toString())
                .build();

        Optional<ProjetoEntity> optionalProjetoEntity = Optional.of(projetoEntity);

        ProjetoDTO projetoDTO = ProjetoDTO.builder()
                .id(1l)
                .nome("Projeto Teste - Teste")
                .dataInicio(LocalDateTime.now())
                .gerenteResponsavel("Gerente X")
                .previsaoTermino(LocalDateTime.now())
                .dataRealTermino(LocalDateTime.now())
                .orcamentoTotal(BigDecimal.ONE)
                .descricao("Descrição do projeto")
                .status(Status.EM_ANDAMENTO.toString())
                .build();

        when(repository.findById(projetoDTO.getId())).thenReturn(optionalProjetoEntity);

        org.junit.jupiter.api.Assertions.assertThrows(ProjetoExclusionException.class, () -> {
            service.delete(projetoDTO.getId());
        });

    }

    @Test
    public void naoDeveDeletarProjetoEncerrado() {

        ProjetoEntity projetoEntity = ProjetoEntity.builder()
                .id(1l)
                .nome("Projeto Teste - Teste")
                .dataInicio(LocalDateTime.now())
                .gerenteResponsavel("Gerente X")
                .previsaoTermino(LocalDateTime.now())
                .dataRealTermino(LocalDateTime.now())
                .orcamentoTotal(BigDecimal.ONE)
                .descricao("Descrição do projeto")
                .status(Status.ENCERRADO.toString())
                .build();

        Optional<ProjetoEntity> optionalProjetoEntity = Optional.of(projetoEntity);

        ProjetoDTO projetoDTO = ProjetoDTO.builder()
                .id(1l)
                .nome("Projeto Teste - Teste")
                .dataInicio(LocalDateTime.now())
                .gerenteResponsavel("Gerente X")
                .previsaoTermino(LocalDateTime.now())
                .dataRealTermino(LocalDateTime.now())
                .orcamentoTotal(BigDecimal.ONE)
                .descricao("Descrição do projeto")
                .status(Status.ENCERRADO.toString())
                .build();

        when(repository.findById(projetoDTO.getId())).thenReturn(optionalProjetoEntity);

        org.junit.jupiter.api.Assertions.assertThrows(ProjetoExclusionException.class, () -> {
            service.delete(projetoDTO.getId());
        });

    }
}
