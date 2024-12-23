package med.voll.api.paciente;
import med.voll.api.direccion.Direccion;

public record DatosRespuestaPaciente(

        String nombre,

        String email,

        String telefono,

        String documentoIdentidad,

        Direccion direccion) {

    public DatosRespuestaPaciente(Paciente paciente) {
        this(paciente.getNombre(), paciente.getEmail(), paciente.getTelefono(),
                paciente.getDocumentoIdentidad(), paciente.getDireccion());
    }
}