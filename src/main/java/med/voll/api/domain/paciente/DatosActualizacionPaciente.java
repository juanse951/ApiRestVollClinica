package med.voll.api.domain.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.direccion.DatosDireccion;

//Dto de actualizacion
public record DatosActualizacionPaciente(

        @NotNull //por que es un Long y es el unico valor que necesitamos obligatoriamente
        Long id,

        String nombre,

        @Email
        String email,

        String telefono,

        String documentoIdentidad,

        @Valid //por que es un objeto
        DatosDireccion direccion
) {
}
