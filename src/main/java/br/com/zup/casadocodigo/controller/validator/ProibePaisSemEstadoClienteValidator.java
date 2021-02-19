package br.com.zup.casadocodigo.controller.validator;

import br.com.zup.casadocodigo.model.Estado;
import br.com.zup.casadocodigo.model.request.ClienteRequest;
import br.com.zup.casadocodigo.repository.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Collection;
import java.util.List;

@Component
public class ProibePaisSemEstadoClienteValidator implements Validator {

    @Autowired
    private EstadoRepository estadoRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return ClienteRequest.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()){
            return;
        }

        ClienteRequest request = (ClienteRequest) target;

        List<Estado> estadosPais = estadoRepository.findAllStatesInCountry(request.getIdPais());

        if (estadosPais.isEmpty()) {
            if(request.getIdEstado() != null) {
                errors.rejectValue("idEstado", null, "Este pais não tem nenhum estado cadastrado");
            }
        } else {
            if (!request.estadoTaNoPais(estadosPais)) {
                errors.rejectValue("idEstado", null, "Não existe esse estado nesse pais");
            }

        }


    }
}
