package com.raphaelfontoura.medvoll.api.controller;

import com.raphaelfontoura.medvoll.api.medico.DadosCadastroMedico;
import com.raphaelfontoura.medvoll.api.medico.Medico;
import com.raphaelfontoura.medvoll.api.medico.MedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("medicos")
@RequiredArgsConstructor
public class MedicoController {

    private final MedicoRepository repository;

    @PostMapping
    public void cadastrar(@RequestBody DadosCadastroMedico dados) {
        repository.save(new Medico(dados))
    }
}
