package br.com.zup.casadocodigo.model.request;

import br.com.zup.casadocodigo.model.Pais;
import br.com.zup.casadocodigo.shared.UniqueValue;

import javax.validation.constraints.NotBlank;

public class PaisRequest {

    @NotBlank
    @UniqueValue(domainClass = Pais.class, fieldName = "nome")
    private String nome;

    public PaisRequest(@NotBlank String nome) {
        this.nome = nome;
    }

    @Deprecated
    public PaisRequest() {
    }

    public String getNome() {
        return nome;
    }
}
