package br.com.desbravador.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PessoaDTO {

    private Long id;
    private String nome;
    private Boolean funcionario;
}
