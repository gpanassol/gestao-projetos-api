package br.com.desbravador.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "PROJETO")
public class ProjetoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROJETO_SEQ")
    @SequenceGenerator(name = "PROJETO_SEQ", sequenceName = "PROJETO_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DATA_INICIO")
    private LocalDateTime dataInicio;

    @Column(name = "GERENTE_RESPONSAVEL")
    private String gerenteResponsavel;

    @Column(name = "PREVISAO_TERMINO")
    private LocalDateTime previsaoTermino;

    @Column(name = "DATA_REAL_TERMINO")
    private LocalDateTime dataRealTermino;

    @Column(name = "ORCAMENTO_TOTAL")
    private BigDecimal orcamentoTotal;

    @Column(name = "DESCRICAO")
    private String descricao;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "RISCO")
    private String classificacaoRisco;

}
