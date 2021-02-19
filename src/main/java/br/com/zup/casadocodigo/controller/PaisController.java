package br.com.zup.casadocodigo.controller;

import br.com.zup.casadocodigo.model.Pais;
import br.com.zup.casadocodigo.model.request.PaisRequest;
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
@RequestMapping("/paises")
public class PaisController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<Pais> criar(@RequestBody @Valid PaisRequest form) {
        Pais estado = new Pais(form.getNome());

        manager.persist(estado);

        return ResponseEntity.ok(estado);
    }

}
