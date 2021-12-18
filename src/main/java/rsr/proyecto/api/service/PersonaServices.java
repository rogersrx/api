package rsr.proyecto.api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import rsr.proyecto.api.model.Persona;

public interface PersonaServices {

    public Page<Persona> findAll(Pageable page);

    public Persona findById(Long id);

    public Persona save(Persona dto);

    public void delete(Long id);
}
