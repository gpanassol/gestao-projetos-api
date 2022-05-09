package br.com.desbravador.controllers;

import br.com.desbravador.dto.PessoaDTO;
import br.com.desbravador.services.PessoaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Validated
@RestController
@RequestMapping("/v1/pessoa")
public class PessoaControllerV1 {

    @Autowired
    private PessoaService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity findAll() {
        List<PessoaDTO> allProjeto = service.findAllPessoa();
        if (allProjeto.size() > 0) {
            return ResponseEntity.ok(allProjeto);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<PessoaDTO> findProductById(@PathVariable Long id) {

        PessoaDTO pessoaDTO = service.findById(id);
        if (pessoaDTO != null) {
            return ResponseEntity.ok(pessoaDTO);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PessoaDTO> save(@RequestBody @Validated PessoaDTO dto) {

        PessoaDTO pessoaDTO = service.save(dto);
        if (pessoaDTO != null) {
            return ResponseEntity.ok(pessoaDTO);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PessoaDTO> save(@PathVariable Long id,
                                           @RequestBody @Validated PessoaDTO dto) {

        PessoaDTO pessoaDTO = service.update(id, dto);
        if (pessoaDTO != null) {
            return ResponseEntity.ok(pessoaDTO);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }


}
