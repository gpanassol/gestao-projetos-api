package br.com.desbravador.controllers;

import br.com.desbravador.dto.ProjetoDTO;
import br.com.desbravador.services.ProjetoService;
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
@RequestMapping("/v1/projeto")
public class ProjetoControllerV1 {

    @Autowired
    private ProjetoService service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity findAll() {
        List<ProjetoDTO> allProjeto = service.findAllProjeto();
        if (allProjeto.size() > 0) {
            return ResponseEntity.ok(allProjeto);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<ProjetoDTO> findProductById(@PathVariable Long id) {

        ProjetoDTO projetoDTO = service.findById(id);
        if (projetoDTO != null) {
            return ResponseEntity.ok(projetoDTO);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProjetoDTO> save(@RequestBody @Validated ProjetoDTO dto) {

        ProjetoDTO projetoDTO = service.save(dto);
        if (projetoDTO != null) {
            return ResponseEntity.ok(projetoDTO);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProjetoDTO> save(@PathVariable Long id,
                                           @RequestBody @Validated ProjetoDTO dto) {

        ProjetoDTO projetoDTO = service.update(id, dto);
        if (projetoDTO != null) {
            return ResponseEntity.ok(projetoDTO);
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
