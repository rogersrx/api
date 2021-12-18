package rsr.proyecto.api.model;

import com.sun.istack.NotNull;
import rsr.proyecto.api.Util;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "persona")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty(message = Util.NO_VACIO)
    private String nombre;
    @NotEmpty(message = Util.NO_VACIO)
    private String apellido;
    @NotEmpty(message = Util.NO_VACIO)
    @Size(min = 8,max = 8,message = " valido de 8 digitos")
    @Pattern(regexp = "[\\\\s]*[0-9]*[1-9]+", message = " acepta solo números")
    private String dni;
    @NotEmpty(message = Util.NO_VACIO)
    @Size(min = 2,max = 2,message = " permite solo: SI o NO")
    private String empleado;

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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }
}
