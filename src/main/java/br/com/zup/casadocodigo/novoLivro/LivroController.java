package br.com.zup.casadocodigo.novoLivro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public String criar(@RequestBody @Valid NovoLivroRequest request) {
        Livro livro = request.toModel(manager);

        manager.persist(livro);

        return livro.toString();

    }

    @GetMapping
    @Transactional(readOnly = true)
    public List<RetornoLivro> listar() {
        return manager.createQuery("select l from Livro l", Livro.class)
                .getResultStream()
                .map(RetornoLivro::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<Livro> buscar(@PathVariable Long id) {
        Livro livro = Optional.ofNullable(manager.find(Livro.class, id))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        System.out.println(livro);

        return ResponseEntity.ok(livro);

    }

}
