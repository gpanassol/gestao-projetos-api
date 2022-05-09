package br.com.desbravador.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MEMBRO")
public class MembroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBRO_SEQ")
    @SequenceGenerator(name = "MEMBRO_SEQ", sequenceName = "MEMBRO_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @MapsId("FK_MEMBRO_PESSOA")
    @JoinColumn(name = "PROJETO_ID", insertable = false, updatable = false)
    private ProjetoEntity idProjeto;

    @ManyToOne
    @MapsId("FK_PESSOA")
    @JoinColumn(name = "PESSOA_ID", insertable = false, updatable = false)
    private PessoaEntity idPessoa;
}
