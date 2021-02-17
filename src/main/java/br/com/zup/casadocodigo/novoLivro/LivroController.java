package br.com.zup.casadocodigo.novoLivro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
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
    @Transactional
    public List<RetornoLivro> listar() {
        return manager.createQuery("select l from Livro l", Livro.class)
                .getResultStream()
                .map(RetornoLivro::new)
                .collect(Collectors.toList());
    }

}
