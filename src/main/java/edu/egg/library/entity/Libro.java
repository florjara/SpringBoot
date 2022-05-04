package edu.egg.library.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Libro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "libro_id", nullable = false)
    private Long id;

    @Column(name = "libro_isbn", nullable = false)
    private Long isbn;

    @Column(name = "libro_titulo", length=60, nullable = false)
    private String titulo;

    @Column(name = "libro_ejemplares", nullable = false)
    private Short ejemplares;

    @Column(name = "libro_prestados", nullable = false)
    private Short ejemplaresPrestados;

    @Column(name = "libro_restantes", nullable = false)
    private Short ejemplaresRestantes;

    @Column(name = "libro_alta", nullable = false)
    private boolean alta;

    @Column(name = "libro_anio", columnDefinition = "year", nullable = false)
    private short anio;

    @ManyToOne(fetch = FetchType.LAZY) //Eager -> carga ansiosa, es decir, estos datos se cargan al consultar
    @JoinColumn(name = "libro_autor", referencedColumnName = "autor_id")
    private Autor autor;

    @ManyToOne(fetch = FetchType.LAZY) //Eager -> carga ansiosa, es decir, estos datos se cargan al consultar
    @JoinColumn(name = "libro_editorial", referencedColumnName = "editorial_id")
    private Editorial editorial;

    public Libro() {
    }

    public Libro(Long id, Long isbn, String titulo, Short ejemplares, Short ejemplaresPrestados, Short ejemplaresRestantes, boolean alta, short anio, Autor autor, Editorial editorial) {
        this.id = id;
        this.isbn = isbn;
        this.titulo = titulo;
        this.ejemplares = ejemplares;
        this.ejemplaresPrestados = ejemplaresPrestados;
        this.ejemplaresRestantes = ejemplaresRestantes;
        this.alta = alta;
        this.anio = anio;
        this.autor = autor;
        this.editorial = editorial;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public short getAnio() {
        return anio;
    }

    public void setAnio(short anio) {
        this.anio = anio;
    }


    public Long getIsbn() {
        return isbn;
    }

    public void setIsbn(Long isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Short getEjemplares() {
        return ejemplares;
    }

    public void setEjemplares(Short ejemplares) {
        this.ejemplares = ejemplares;
    }

    public Short getEjemplaresPrestados() {
        return ejemplaresPrestados;
    }

    public void setEjemplaresPrestados(Short ejemplaresPrestados) {
        this.ejemplaresPrestados = ejemplaresPrestados;
    }

    public Short getEjemplaresRestantes() {
        return ejemplaresRestantes;
    }

    public void setEjemplaresRestantes(Short ejemplaresRestantes) {
        this.ejemplaresRestantes = ejemplaresRestantes;
    }

    public boolean isAlta() {
        return alta;
    }

    public void setAlta(boolean alta) {
        this.alta = alta;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Editorial getEditorial() {
        return editorial;
    }

    public void setEditorial(Editorial editorial) {
        this.editorial = editorial;
    }

    @Override
    public String toString() {
        String imprimir = String.format("%-5s%-20s%-10s%-10s%-10s%-10s", isbn, titulo, ejemplares, ejemplaresPrestados, ejemplaresRestantes, alta);
        if (autor != null) {
            imprimir = imprimir + String.format("%-15s", autor.getNombre());
        }
        if (editorial != null) {
            imprimir = imprimir + String.format("%-15s", editorial.getNombre());
        }
        return imprimir;
    }

}
