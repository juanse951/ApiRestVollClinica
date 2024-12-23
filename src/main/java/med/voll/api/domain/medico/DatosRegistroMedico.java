package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.direccion.DatosDireccion;
//Dto representa la informacion que llega
public record DatosRegistroMedico(

        @NotBlank //valida que nombre no llegue ni nulo ni blanco
        String nombre,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String telefono,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}") //solo de 4 a 6 numeros
        String documento,

        @NotNull
        Especialidad especialidad,

        @NotNull //por que es un objeto
        @Valid //internamente valida que la direccion contenga all
        DatosDireccion direccion
) {

}
