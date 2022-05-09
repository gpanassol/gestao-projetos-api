package br.com.desbravador.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjetoDTO {

    private Long id;
    private String nome;
    private LocalDateTime dataInicio;
    private String gerenteResponsavel;
    private LocalDateTime previsaoTermino;
    private LocalDateTime dataRealTermino;
    private BigDecimal orcamentoTotal;
    private String descricao;
    private String status;

}
