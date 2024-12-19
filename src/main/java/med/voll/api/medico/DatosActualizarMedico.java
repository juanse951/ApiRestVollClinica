package med.voll.api.medico;

import jakarta.validation.constraints.NotNull;
import lombok.Lombok;
import med.voll.api.direccion.DatosDireccion;

import java.lang.annotation.Documented;

//DTO para actulizar la informacion
public record DatosActualizarMedico(

        @NotNull //por que es un Long y es el unico valor que necesitamos obligatoriamente
        Long id,

        String nombre,

        String documento,

        DatosDireccion direccion
) {
}
