package med.voll.api.domain.consulta.validaciones.cancelamiento;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosCancelamientoConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidadorConsultaConAnticipacionCancelamiento")
public class ValidadorConsultaConAnticipacion implements ValidadorCancelamientoDeConsulta {

    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validar(DatosCancelamientoConsulta datos) {
        var consulta = repository.getReferenceById(datos.idConsulta());
        var ahora = LocalDateTime.now();
        var diferenciaEnHoras = Duration.between(ahora, consulta.getFecha()).toHours();

        if (diferenciaEnHoras < 24) {
            throw new ValidacionException("¡La consulta solo puede ser cancelada con anticipación mínima de 24 horas!");
        }
    }
}
