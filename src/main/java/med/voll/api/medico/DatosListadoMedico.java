package med.voll.api.medico;

//Dto representa la informacion que sale
public record DatosListadoMedico(

        Long id,

        String nombre,

        String especialidad,

        String documento,

        String email
) {

    public  DatosListadoMedico(Medico medico){
        this(medico.getId(), medico.getNombre(), medico.getEspecialidad().toString(),
                medico.getDocumento(), medico.getEmail());
    }
}
