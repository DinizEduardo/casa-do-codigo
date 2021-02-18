package br.com.zup.casadocodigo.livro;

import br.com.zup.casadocodigo.compartilhado.ExistsId;
import br.com.zup.casadocodigo.compartilhado.UniqueValue;
import br.com.zup.casadocodigo.categoria.Categoria;
import br.com.zup.casadocodigo.autor.Autor;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

public class NovoLivroRequest {
    @NotBlank
    @UniqueValue(domainClass = Livro.class, fieldName = "titulo")
    private String titulo;

    @NotBlank
    private String resumo;

    @NotBlank
    private String sumario;

    @NotNull
    @Min(20)
    private BigDecimal preco;

    @NotNull
    @Min(100)
    private Integer paginas;

    @NotBlank
    @UniqueValue(domainClass = Livro.class, fieldName = "isbn")
    private String isbn;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataLancamento;

    @NotNull
    @ExistsId(domainClass = Categoria.class, fieldName = "id")
    private Long idCategoria;

    @NotNull
    @ExistsId(domainClass = Autor.class, fieldName = "id")
    private Long idAutor;

    public NovoLivroRequest(@NotBlank String titulo,
                            @NotBlank String resumo,
                            @NotBlank String sumario,
                            @NotNull @Min(20) BigDecimal preco,
                            @NotNull @Min(100) Integer paginas,
                            @NotBlank String isbn,
                            @NotNull Long idCategoria,
                            @NotNull Long idAutor) {
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.paginas = paginas;
        this.isbn = isbn;
        this.idCategoria = idCategoria;
        this.idAutor = idAutor;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getPaginas() {
        return paginas;
    }

    public String getIsbn() {
        return isbn;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public Long getIdAutor() {
        return idAutor;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    public Livro toModel(EntityManager manager) {
        @NotNull Autor autor = manager.find(Autor.class, this.idAutor);
        @NotNull Categoria categoria = manager.find(Categoria.class, this.idCategoria);

        Assert.state(autor!=null, "Você está querendo cadastrar um livro para um autor que não existe: " + this.idAutor);
        Assert.state(categoria!=null, "Você está querendo cadastrar um livro para uma categoria que não existe: " + this.idCategoria);

        return new Livro(this.titulo, this.resumo, this.sumario, this.preco,
                        this.paginas, this.isbn, this.dataLancamento, categoria, autor);

    }
}