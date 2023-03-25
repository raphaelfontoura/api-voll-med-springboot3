package com.raphaelfontoura.medvoll.api.controller;

import com.raphaelfontoura.medvoll.api.medico.DadosCadastroMedico;
import com.raphaelfontoura.medvoll.api.medico.DadosListagemMedico;
import com.raphaelfontoura.medvoll.api.medico.Medico;
import com.raphaelfontoura.medvoll.api.medico.MedicoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("medicos")
@RequiredArgsConstructor
public class MedicoController {

    private final MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
        repository.save(new Medico(dados));
    }

    @GetMapping
    public List<DadosListagemMedico> listar() {
        return repository.findAll().stream().map(DadosListagemMedico::new).toList();
    }
}
