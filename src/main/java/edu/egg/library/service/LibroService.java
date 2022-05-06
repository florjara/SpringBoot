package edu.egg.library.service;

import edu.egg.library.entity.Libro;
import edu.egg.library.repository.LibroRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LibroService {

    @Autowired
    private LibroRepository libroRepository;


    @Transactional
    public void crearLibro(Libro libroDto) {
        Libro libro = new Libro();

        Short s = 0; //como lo meto parametro 
        libro.setTitulo(libroDto.getTitulo());
        libro.setIsbn(libroDto.getIsbn());
        libro.setEjemplaresPrestados(s);
        libro.setEjemplaresRestantes(s);
        libro.setEjemplares(libroDto.getEjemplares());
        libro.setAnio(libroDto.getAnio());
        libro.setAlta(true);
        libro.setEditorial(libroDto.getEditorial());
        libro.setAutor(libroDto.getAutor());
        
        libroRepository.save(libro);
    }

    @Transactional
    public void modificarLibro(Libro libroDto) {
        Libro libro = libroRepository.getById(libroDto.getId());

        libro.setTitulo(libroDto.getTitulo());
        libro.setIsbn(libroDto.getIsbn());
        libro.setEjemplaresPrestados(libroDto.getEjemplaresPrestados());
        libro.setEjemplaresRestantes(libroDto.getEjemplaresRestantes());
        libro.setEjemplares(libroDto.getEjemplares());
        libro.setAnio(libroDto.getAnio());

        // que onda el autor y la editorial
        libroRepository.save(libro);
    }

    @Transactional(readOnly = true)
    public List<Libro> obtenerLibros() {
        return libroRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Libro obtenerLibroPorId(Long id) {
        return libroRepository.getById(id);
    }

    @Transactional
    public void eliminarLibroPorId(Long id) {
        libroRepository.deleteById(id);
    }

}
