package med.voll.api.controller;

import med.voll.api.domain.direccion.DatosDireccion;
import med.voll.api.domain.medico.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class MedicoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DatosRegistroMedico> datosRegistroMedicoJson;

    @Autowired
    private JacksonTester<DatosRespuestaMedico> datosRespuestaMedicoJson;

    @MockBean
    private MedicoRepository repository;

    @Test
    @DisplayName("Debería devolver código http 400 cuando las informaciones son inválidas")
    @WithMockUser //nos ayuda a simular que ya estamos logados
    void registrar_escenario1() throws Exception {
        //given o arrange -->dando, un contexto
        var response = mvc
                .perform(post("/medicos"))//enviamos consulta sin json por eso debe dar 400
                .andReturn().getResponse(); //guardamos la respuesta que deberia ser un 400 en una variable
        //then o assert --> entonces
        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Debería devolver código http 200 cuando las informaciones son válidas")
    @WithMockUser
    void registrar_escenario2() throws Exception {
        var datosRegistro = new DatosRegistroMedico(
                "Medico",
                "medico@voll.med",
                "61999999999",
                "123456",
                Especialidad.CARDIOLOGIA,
                datosDireccion());

        when(repository.save(any())).thenReturn(new Medico(datosRegistro));

        var response = mvc //Obtiene el código de estado HTTP de la respuesta devuelta por la solicitud que realizaste en el test
                .perform(post("/medicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(datosRegistroMedicoJson.write(datosRegistro).getJson()))
                .andReturn().getResponse();

        var datosRespuesta = new DatosRespuestaMedico(
                null,
                datosRegistro.nombre(),
                datosRegistro.email(),
                datosRegistro.telefono(),
                datosRegistro.documento(),
                datosRegistro.especialidad(),
                datosRegistro.direccion()
        );
        var jsonEsperado = datosRespuestaMedicoJson.write(datosRespuesta).getJson();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

    private DatosDireccion datosDireccion() {
        return new DatosDireccion(
                "calle x",
                "distrito y",
                "ciudad z",
                "123",
                "1"
        );
    }

}