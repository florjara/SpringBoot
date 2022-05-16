package edu.egg.library.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@SQLDelete(sql = "UPDATE autor SET autor_alta = FALSE WHERE autor_id = ?") 
//@Where(clause = "autor_alta=TRUE")
public class Autor {

    @Id // al definirlo id es automaticamente no null
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "autor_id", nullable = false)
    private Long id;

    @Column(name = "autor_nombre", length=60, nullable = false)
    private String nombre;

    @Column(name = "autor_alta", nullable = false)
    private boolean alta;

    public Autor() {
    }

    public Autor(Long id, String nombre, boolean alta) {
        this.id = id;
        this.nombre = nombre;
        this.alta = alta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isAlta() {
        return alta;
    }

    public void setAlta(boolean alta) {
        this.alta = alta;
    }

    @Override
    public String toString() {
        return String.format("%-5s%-15s%-10s", id, nombre, alta);
    }

}
