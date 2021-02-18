package br.com.zup.casadocodigo.estado;

import br.com.zup.casadocodigo.compartilhado.ExistsId;
import br.com.zup.casadocodigo.compartilhado.UniqueStateValue;
import br.com.zup.casadocodigo.pais.Pais;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EstadoRequest {
    @NotBlank
    @UniqueStateValue(domainClass = Estado.class, fieldName = "nome", message = "Nome do estado já cadastrado")
    private String nome;

    @ExistsId(domainClass = Pais.class, fieldName = "id")
    @NotNull
    private Long idPais;

    public EstadoRequest(String nome, Long idPais) {
        this.nome = String.format("%s:%d", nome, idPais);
        this.idPais = idPais;
    }

    public String getNome() {
        return nome;
    }

    public Long getIdPais() {
        return idPais;
    }

    public Estado toModel(EntityManager manager) {
        @NotNull Pais pais = manager.find(Pais.class, this.idPais);

        Assert.state(pais!= null, "Você está tentando cadastrar em um pais que não existe no nosso banco");

        return new Estado(nome.split(":")[0], pais);



    }
}
