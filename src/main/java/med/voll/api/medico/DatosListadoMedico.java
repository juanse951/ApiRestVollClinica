package med.voll.api.medico;

//Dto representa la informacion que sale
public record DatosListadoMedico(
        String nombre,

        String especialidad,

        String documento,

        String email
) {

    public  DatosListadoMedico(Medico medico){
        this(medico.getNombre(), medico.getEspecialidad().toString(),
                medico.getDocumento(), medico.getEmail());
    }
}
