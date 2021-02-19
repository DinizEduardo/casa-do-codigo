package br.com.zup.casadocodigo.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String sobrenome;

    @NotBlank
    private String documento;

    @NotBlank
    private String endereco;

    @NotNull
    @ManyToOne
    private Cidade cidade;

    @NotNull
    @ManyToOne
    private Estado estado;

    @NotNull
    @ManyToOne
    private Pais pais;

    @NotBlank
    private String telefone;

    @NotBlank
    private String cep;

    public Cliente(@Email @NotBlank String email,
                   @NotBlank String nome,
                   @NotBlank String sobrenome,
                   @NotBlank String documento,
                   @NotBlank String endereco,
                   @NotNull Cidade cidade,
                   @NotNull Estado estado,
                   @NotNull Pais pais,
                   @NotBlank String telefone,
                   @NotBlank String cep) {
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.documento = documento.replaceAll("[^\\d]", "");
        this.endereco = endereco;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
        this.telefone = telefone;
        this.cep = cep;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEndereco() {
        return endereco;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public Estado getEstado() {
        return estado;
    }

    public Pais getPais() {
        return pais;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }
}
