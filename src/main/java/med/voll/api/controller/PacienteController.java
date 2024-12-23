package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private PacienteRepository pacienteRepository;

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaPaciente> registrarPaciente(@RequestBody @Valid DatosRegistroPaciente datosRegistroPaciente,
                                                                    UriComponentsBuilder uriComponentsBuilder){
        Paciente paciente = pacienteRepository.save(new Paciente(datosRegistroPaciente));

        var uri = uriComponentsBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosRespuestaPaciente(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<DatosListaPaciente>> listar(@PageableDefault(size = 10, sort = {"nombre"})Pageable paginacion){
        var page = pacienteRepository.findAllByActivoTrue(paginacion).map(DatosListaPaciente::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public void actualizar(@RequestBody @Valid DatosActualizacionPaciente datos){
        Paciente paciente = pacienteRepository.getReferenceById(datos.id());
        paciente.actualizarInformacion(datos);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void remover(@PathVariable Long id){
        Paciente paciente = pacienteRepository.getReferenceById(id);
        paciente.inactivar();
    }
}
