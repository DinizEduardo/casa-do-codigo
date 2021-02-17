package br.com.zup.casadocodigo.NovaCategoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Validator;
import org.springframework.web.bind.WebDataBinder;
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

    @Autowired
    private ProibeNomeDeCategoriaDuplicadoValidator proibeNomeDeCategoriaDuplicadoValidator;

    @InitBinder
    public void init(WebDataBinder binder) {
        binder.addValidators(proibeNomeDeCategoriaDuplicadoValidator);
    }

    @PostMapping
    @Transactional
    public String cadastrar(@RequestBody @Valid NovaCategoriaRequest request) {
        Categoria categoria = request.toModel();

        manager.persist(categoria);

        return categoria.toString();
    }

}
