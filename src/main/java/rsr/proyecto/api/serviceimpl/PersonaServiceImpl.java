package rsr.proyecto.api.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rsr.proyecto.api.model.Persona;
import rsr.proyecto.api.repository.PersonaRepository;
import rsr.proyecto.api.service.PersonaServices;



@Service
public class PersonaServiceImpl implements PersonaServices {

    @Autowired
    private PersonaRepository personaRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Persona> findAll(Pageable page) {
        return personaRepository.findAll(page);
    }

    @Override
    @Transactional(readOnly = true)
    public Persona findById(Long id) {
        return personaRepository.findById(id).orElse(new Persona());
    }

    @Override
    @Transactional
    public Persona save(Persona dto) {
        personaRepository.save(dto);
        return dto;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        personaRepository.deleteById(id);

    }
}
