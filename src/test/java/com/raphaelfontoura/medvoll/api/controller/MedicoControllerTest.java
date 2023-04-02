package com.raphaelfontoura.medvoll.api.controller;

import com.raphaelfontoura.medvoll.api.domain.endereco.DadosEndereco;
import com.raphaelfontoura.medvoll.api.domain.endereco.Endereco;
import com.raphaelfontoura.medvoll.api.domain.medico.*;
import org.assertj.core.api.Assertions;
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

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
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
    private JacksonTester<DadosCadastroMedico> dadosCadastroMedicoJSON;

    @Autowired
    private JacksonTester<DadosDetalhamentoMedico> dadosDetalhamentoMedicoJSON;

    @MockBean
    private MedicoRepository medicoRepository;

    @Test
    @DisplayName("Deveria devolver codigo http 400 quando os dados sao invalidos")
    @WithMockUser
    void cadastrarCenario1() throws Exception {

        var response = mvc.perform(post("/medicos"))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 201 quando os dados sao validos")
    @WithMockUser
    void cadastrarCenario2() throws Exception {
        var dadosCadastro = new DadosCadastroMedico("Medico", "medico@voll.med", "2211-2121", "12345", Especialidade.ORTOPEDIA, getDadosEndereco());
        var medico = new Medico(dadosCadastro);
        var dadosReturn = new DadosDetalhamentoMedico(null,
                dadosCadastro.nome(),
                dadosCadastro.email(),
                dadosCadastro.telefone(),
                dadosCadastro.crm(),
                dadosCadastro.especialidade(),
                new Endereco(getDadosEndereco()));
        var dadosInputJson = dadosCadastroMedicoJSON.write(dadosCadastro).getJson();
        var dadosReturnJson = dadosDetalhamentoMedicoJSON.write(dadosReturn).getJson();
        when(medicoRepository.save(any())).thenReturn(medico);

        var response = mvc.perform(post("/medicos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(dadosInputJson))
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(response.getContentAsString()).isEqualTo(dadosReturnJson);
    }

    private static DadosEndereco getDadosEndereco() {
        var dadosEndereco = new DadosEndereco("rua alguma",
                "bairro",
                "11000200",
                "cidade",
                "UF",
                null,
                "42");
        return dadosEndereco;
    }
}