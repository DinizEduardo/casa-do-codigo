package br.com.zup.casadocodigo.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "paises")
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String nome;

    public Pais(@NotBlank String nome) {
        this.nome = nome;
    }

    @Deprecated
    public Pais() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
