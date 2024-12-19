package med.voll.api.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.direccion.DatosDireccion;

//Dto de actualizacion
public record DatosActualizacionPaciente(

        @NotNull //por que es un Long y es el unico valor que necesitamos obligatoriamente
        Long id,

        String nombre,

        String telefono,

        @Valid //por que es un objeto
        DatosDireccion direccion
) {
}
