package br.com.zup.casadocodigo.categoria;

import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @PersistenceContext
    private EntityManager manager;

    @PostMapping
    @Transactional
    public String cadastrar(@RequestBody @Valid NovaCategoriaRequest request) {
        Categoria categoria = request.toModel();

        manager.persist(categoria);

        return categoria.toString();
    }

}
