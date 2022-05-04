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
    private LibroRepository repository;

    @Transactional
    public void crearLibro(Libro libroDto) {
        Libro libro = new Libro();

        libro.setTitulo(libroDto.getTitulo());
        libro.setIsbn(libroDto.getIsbn());
        libro.setEjemplaresPrestados(libroDto.getEjemplaresPrestados());
        libro.setEjemplaresRestantes(libroDto.getEjemplaresRestantes());
        libro.setEjemplares(libroDto.getEjemplares());
        libro.setAnio(libroDto.getAnio());
        libro.setAlta(true);

        repository.save(libro);
    }

    @Transactional
    public void modificarLibro(Libro libroDto) {
        Libro libro = repository.getById(libroDto.getId());
        
        libro.setTitulo(libroDto.getTitulo());
        libro.setIsbn(libroDto.getIsbn());
        libro.setEjemplaresPrestados(libroDto.getEjemplaresPrestados());
        libro.setEjemplaresRestantes(libroDto.getEjemplaresRestantes());
        libro.setEjemplares(libroDto.getEjemplares());
        libro.setAnio(libroDto.getAnio());

        // que onda el autor y la editorial
        repository.save(libro);
    }

    @Transactional(readOnly = true)
    public List<Libro> obtenerLibros() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Libro obtenerLibroPorId(Long id) {
        return repository.getById(id);
    }

    @Transactional
    public void eliminarLibroPorId(Long id) {
        repository.deleteById(id);
    }

}
