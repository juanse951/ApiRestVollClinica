package med.voll.api.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @PostMapping
    public void registrarMedico(@RequestBody String parametro){
        System.out.println("Correcto");
        System.out.println(parametro);
    }
}
