package rsr.proyecto.api.rest;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import rsr.proyecto.api.Util;
import rsr.proyecto.api.model.Persona;
import rsr.proyecto.api.service.PersonaServices;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api")
public class PersonaController {

    @Autowired
    private PersonaServices personaServices;

    @ApiOperation(value = "Lista de Personas", notes = "Listar")
    @GetMapping("/persona/page/{page}")
    public ResponseEntity<Page<Persona>> findAll(@PathVariable Integer page) {
        return ResponseEntity.status(HttpStatus.OK).body(personaServices.findAll(PageRequest.of(page, 1)));
    }

    @ApiOperation(value = "Buscar Persona por Id", notes = "Buscar")
    @GetMapping("/persona/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Persona persona = new Persona();
        Map<String, Object> response = new HashMap<String, Object>();

        try {
            persona = personaServices.findById(id);
        } catch (DataAccessException e) {
            response.put(Util.MENSAJE, Util.ERROR_CONSULTA);
            response.put(Util.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (persona == null) {
            response.put(Util.MENSAJE, "EL persona ID:".concat(id.toString().concat("no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Persona>(persona, HttpStatus.OK);

    }

    @ApiOperation(value = "Registrar Persona Nueva", notes = "Registrar")
    @PostMapping("/persona")
    public ResponseEntity<?> save(@Validated @RequestBody Persona persona, BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> erros = result.getFieldErrors().stream()
                    .map(err -> "El campo " + err.getField() + " " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", erros);
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);

        }
        Persona per = new Persona();
        try {
            per = personaServices.save(persona);
        } catch (DataAccessException e) {
            response.put(Util.MENSAJE, Util.ERROR_CONSULTA);
            response.put(Util.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
        response.put(Util.MENSAJE, "Registro con éxito!");
        response.put("persona", per);
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
    }
    @ApiOperation(value = "Actualizar Persona", notes = "Actualizar")
    @PutMapping("/persona")
    public ResponseEntity<?> update(@RequestBody Persona persona) {
        Map<String, Object> response = new HashMap<String, Object>();
        Persona perso = personaServices.findById(persona.getId());

        if (perso == null) {
            response.put(Util.MENSAJE,
                    "EL Persona ID:".concat(persona.getId().toString().concat("no existe en la base de datos!")));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
        }
        try {

            personaServices.save(persona);
        } catch (DataAccessException e) {
            response.put(Util.MENSAJE, Util.ERROR_CONSULTA);
            response.put(Util.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put(Util.MENSAJE, "Se actualizo ocrrectamente!");
        response.put("persona", perso);

        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);

    }
    @ApiOperation(value = "Eliminar Persona", notes = "Eliminar")
    @DeleteMapping("/persona/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<String, Object>();
        try {
            personaServices.delete(id);
        } catch (DataAccessException e) {
            response.put(Util.MENSAJE, Util.ERROR_CONSULTA);
            response.put(Util.ERROR, e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        response.put(Util.MENSAJE, "Persona eliminado");
        return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);

    }


}
