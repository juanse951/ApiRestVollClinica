package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.direccion.DatosDireccion;

//DTO para actulizar la informacion
public record DatosActualizarMedico(

        @NotNull //por que es un Long y es el unico valor que necesitamos obligatoriamente
        Long id,

        String nombre,

        String documento,

        @Valid // por ser un objeto
        DatosDireccion direccion
) {
}
