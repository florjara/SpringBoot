package edu.egg.library.service;

import edu.egg.library.entity.Autor;
import edu.egg.library.repository.AutorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AutorService {

    @Autowired
    private AutorRepository repository;

    @Transactional
    public void crearAutor(Autor autorDto) {
        Autor autor = new Autor();

        autor.setNombre(autorDto.getNombre());
        autor.setAlta(true);

        repository.save(autor);
    }

    @Transactional
    public void actualizarAutor(Autor autorDto) {
        Autor autor = repository.getById(autorDto.getId());
        autor.setNombre(autorDto.getNombre());
        repository.save(autor);
    }

    @Transactional(readOnly = true)
    public List<Autor> obtenerAutores() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Autor obtenerAutorPorId(Long id) {
        return repository.getById(id);
    }

    @Transactional
    public void eliminarAutorPorId(Long id) {
        repository.deleteById(id);
    }

}
