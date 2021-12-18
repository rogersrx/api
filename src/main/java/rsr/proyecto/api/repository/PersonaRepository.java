package rsr.proyecto.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rsr.proyecto.api.model.Persona;

public interface PersonaRepository extends JpaRepository<Persona,Long> {
}
