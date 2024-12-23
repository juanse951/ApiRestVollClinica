package med.voll.api.domain.paciente;
//Dto representa la informacion que sale
public record DatosListaPaciente(

        Long id,

        String nombre,

        String email,

        String documentoIdentidad) {

    public DatosListaPaciente(Paciente paciente){
        this(paciente.getId(), paciente.getNombre(), paciente.getEmail(), paciente.getDocumentoIdentidad());
    }
}
