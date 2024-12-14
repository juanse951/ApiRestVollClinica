package med.voll.api.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.direccion.DatosDireccion;
//Dto
public record DatosRegistroMedico(

        @NotBlank //valida que nombre no llegue ni nulo ni blanco
        String nombre,

        @NotBlank
        @Email
        String email,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}") //solo de 4 a 6 numeros
        String documento,

        @NotNull
        Especialidad especialidad,

        @NotNull //por que es un objeto
        @Valid
        DatosDireccion direccion
) {
}
