package med.voll.api.paciente;
import med.voll.api.direccion.DatosDireccion;
import med.voll.api.direccion.Direccion;

public record DatosRespuestaPaciente(

        Long id,

        String nombre,

        String email,

        String telefono,

        String documentoIdentidad,

        DatosDireccion direccion) {


}