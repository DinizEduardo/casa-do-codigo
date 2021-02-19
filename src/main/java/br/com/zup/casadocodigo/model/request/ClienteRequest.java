package br.com.zup.casadocodigo.model.request;

import br.com.zup.casadocodigo.model.Cidade;
import br.com.zup.casadocodigo.model.Cliente;
import br.com.zup.casadocodigo.shared.CpfCnpj;
import br.com.zup.casadocodigo.shared.UniqueValue;
import br.com.zup.casadocodigo.model.Estado;
import br.com.zup.casadocodigo.model.Pais;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ClienteRequest {
    @Email
    @NotBlank
    @UniqueValue(domainClass = Cliente.class, fieldName = "email")
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String sobrenome;

    @NotBlank
    @UniqueValue(domainClass = Cliente.class, fieldName = "documento")
    @CpfCnpj
    private String documento;

    @NotBlank
    private String endereco;

    @NotNull
    private Long idCidade;

    @NotNull
    private Long idEstado;

    @NotNull
    private Long idPais;

    @NotBlank
    private String telefone;

    @NotBlank
    private String cep;

    public ClienteRequest(@Email @NotBlank String email,
                          @NotBlank String nome,
                          @NotBlank String sobrenome,
                          @NotBlank String documento,
                          @NotBlank String endereco,
                          @NotNull Long idCidade,
                          @NotNull Long idEstado,
                          @NotNull Long idPais,
                          @NotBlank String telefone,
                          @NotBlank String cep) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento;
        this.endereco = endereco;
        this.idCidade = idCidade;
        this.idEstado = idEstado;
        this.idPais = idPais;
        this.telefone = telefone;
        this.cep = cep;
    }

    @Override
    public String toString() {
        return "ClienteRequest{" +
                "email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", documento='" + documento + '\'' +
                ", endereco='" + endereco + '\'' +
                ", idCidade=" + idCidade +
                ", idEstado=" + idEstado +
                ", idPais=" + idPais +
                ", telefone='" + telefone + '\'' +
                ", cep='" + cep + '\'' +
                '}';
    }

    public Cliente toModel(EntityManager manager) {
        @NotNull Pais pais = manager.find(Pais.class, this.idPais);
        @NotNull Estado estado = manager.find(Estado.class, this.idEstado);
        @NotNull Cidade cidade = manager.find(Cidade.class, this.idCidade);

        return new Cliente(email, nome, sobrenome, documento, endereco,
                cidade, estado, pais, telefone, cep);
    }

    public Long getIdCidade() {
        return idCidade;
    }

    public Long getIdEstado() {
        return idEstado;
    }

    public Long getIdPais() {
        return idPais;
    }
}
