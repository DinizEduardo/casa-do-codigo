package br.com.zup.casadocodigo.NovoAutor;

import br.com.zup.casadocodigo.NovoAutor.Autor;
import br.com.zup.casadocodigo.NovoAutor.NovoAutorRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
