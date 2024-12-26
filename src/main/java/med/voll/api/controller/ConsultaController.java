package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.DatosDetalleConsulta;
import med.voll.api.domain.consulta.DatosReservaConsulta;
import med.voll.api.domain.consulta.ReservaDeConsultas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
//se encarga de que el flujo de datos este correcto,recibe unas request
//llama algo, ejemplo reglas de negocio y devuelve datos

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private ReservaDeConsultas reserva;

    @PostMapping
    @Transactional
    public ResponseEntity reservar(@RequestBody @Valid DatosReservaConsulta datos){//lo que recibimos de la api

        reserva.reservar(datos);

        return ResponseEntity.ok(new DatosDetalleConsulta(null,null,null,null));//lo que devolvemos
    }
}
