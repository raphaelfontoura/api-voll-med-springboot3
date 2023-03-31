package com.raphaelfontoura.medvoll.api.controller;

import com.raphaelfontoura.medvoll.api.domain.consulta.AgendaDeConsultas;
import com.raphaelfontoura.medvoll.api.domain.consulta.DadosAgendamentoConsulta;
import com.raphaelfontoura.medvoll.api.domain.consulta.DadosDetalhamentoConsulta;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consultas")
@RequiredArgsConstructor
public class ConsultaController {

    private final AgendaDeConsultas service;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        return ResponseEntity.ok(service.agendar(dados));
    }
}
