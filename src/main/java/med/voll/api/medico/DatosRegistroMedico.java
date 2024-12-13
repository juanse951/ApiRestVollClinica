package med.voll.api.medico;

import med.voll.api.direccion.DatosDireccion;
//Dto
public record DatosRegistroMedico(
        String nombre,

        String email,

        String documento,

        Especialidad especialidad,

        DatosDireccion direccion
) {
}
