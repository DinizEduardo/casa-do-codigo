package br.com.zup.casadocodigo.NovaCategoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class ProibeNomeDeCategoriaDuplicadoValidator implements Validator {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return NovaCategoriaRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()) {
            return;
        }

        NovaCategoriaRequest request = (NovaCategoriaRequest) target;

        Optional<Categoria> nomeExiste = categoriaRepository.findByNome(request.getNome());

        if(nomeExiste.isPresent()) {
            errors.rejectValue("nome", null,
                    "Já existe uma categoria com esse nome");
        }
    }
}
