package br.com.zup.casadocodigo.controller;

import br.com.zup.casadocodigo.model.Cliente;
import br.com.zup.casadocodigo.model.request.ClienteRequest;
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
@RequestMapping("/clientes")
public class ClienteController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<Cliente> criar(@RequestBody @Valid ClienteRequest form) {

        Cliente cliente = form.toModel(manager);

        manager.persist(cliente);

        return ResponseEntity.ok(cliente);

    }

}
