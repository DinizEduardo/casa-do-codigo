package br.com.zup.casadocodigo.NovoAutor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class ProibeEmailDoAutorDuplicadoValidator implements Validator {

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return NovoAutorRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()) {
            return;
        }

        NovoAutorRequest request = (NovoAutorRequest) target;

        Optional<Autor> emailExiste = autorRepository.findByEmail(request.getEmail());

        if(emailExiste.isPresent()) {
            errors.rejectValue("email", null,
                    "Já existe um(a) autor(a) com esse e-mail");
        }
    }
}
