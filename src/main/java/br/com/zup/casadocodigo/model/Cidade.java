package br.com.zup.casadocodigo.model;

import br.com.zup.casadocodigo.model.Estado;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cidades")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    @ManyToOne
    @NotNull
    private Estado estado;

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
