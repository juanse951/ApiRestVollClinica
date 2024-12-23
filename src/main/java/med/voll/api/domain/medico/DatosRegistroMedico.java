package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.direccion.DatosDireccion;
//Dto representa la informacion que llega
public record DatosRegistroMedico(

        @NotBlank(message = "{nombre.obligatorio}") //valida que nombre no llegue ni nulo ni blanco
        String nombre,

        @NotBlank(message = "{email.obligatorio}")
        @Email(message = "{email.invalido}")
        String email,

        @NotBlank(message = "{telefono.obligatorio}")
        String telefono,

        @NotBlank(message = "{documento.obligatorio}")
        @Pattern(regexp = "\\d{4,6}", message = "{documento.invalido}") //solo de 4 a 6 numeros
        String documento,

        @NotNull(message = "{especialidad.obligatorio}")
        Especialidad especialidad,

        @NotNull(message = "{direccion.obligatorio}") //por que es un objeto
        @Valid //internamente valida que la direccion contenga all
        DatosDireccion direccion
) {

}
