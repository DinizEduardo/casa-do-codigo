package br.com.zup.casadocodigo.autor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/autor")
public class AutorController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public ResponseEntity<Autor> cadastrar(@RequestBody @Valid NovoAutorRequest request) {
        Autor autor = request.toModel();

        manager.persist(autor);

        return ResponseEntity.ok(autor);

    }

}
