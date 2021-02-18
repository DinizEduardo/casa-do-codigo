package br.com.zup.casadocodigo.livro;

public class RetornoLivro {

    private Long id;
    private String titulo;

    public RetornoLivro(Livro livro) {
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
    }

    public RetornoLivro() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    @Override
    public String toString() {
        return "RetornoLivro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                '}';
    }
}
