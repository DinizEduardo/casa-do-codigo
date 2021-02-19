package br.com.zup.casadocodigo.controller;

import br.com.zup.casadocodigo.model.Estado;
import br.com.zup.casadocodigo.model.request.EstadoRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<Estado> criar(@RequestBody @Valid EstadoRequest form) {

        Estado estado = form.toModel(manager);

        manager.persist(estado);

        return ResponseEntity.ok(estado);

    }

}
