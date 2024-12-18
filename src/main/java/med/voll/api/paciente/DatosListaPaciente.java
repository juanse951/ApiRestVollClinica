package med.voll.api.paciente;
//Dto representa la informacion que sale
public record DatosListaPaciente(

        String nombre,

        String email,

        String documentoIdentidad) {

    public DatosListaPaciente(Paciente paciente){
        this(paciente.getNombre(), paciente.getEmail(), paciente.getDocumentoIdentidad());
    }
}
