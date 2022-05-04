package edu.egg.library.service;

import edu.egg.library.entity.Editorial;
import edu.egg.library.repository.EditorialRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EditorialService {

    @Autowired
    private EditorialRepository repository;

    @Transactional
    public void crearEditorial(Editorial editorialDto) {
        Editorial editorial = new Editorial();

        editorial.setNombre(editorialDto.getNombre());
        editorial.setAlta(true);

        repository.save(editorial);
    }

    @Transactional
    public void modificarEditorial(Editorial editorialDto) {
        Editorial editorial = repository.getById(editorialDto.getId());
        editorial.setNombre(editorialDto.getNombre());
        repository.save(editorial);
    }

    @Transactional(readOnly = true)
    public List<Editorial> obtenerEditoriales() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Editorial obtenerEditorialPorId(Long id) {
        return repository.getById(id);
    }

    @Transactional
    public void eliminarEditorialPorId(Long id) {
        repository.deleteById(id);
    }

}
