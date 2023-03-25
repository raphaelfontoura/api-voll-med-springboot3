package com.raphaelfontoura.medvoll.api.controller;

import com.raphaelfontoura.medvoll.api.medico.DadosCadastroMedico;
import com.raphaelfontoura.medvoll.api.medico.DadosListagemMedico;
import com.raphaelfontoura.medvoll.api.medico.Medico;
import com.raphaelfontoura.medvoll.api.medico.MedicoRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


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
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        return repository.findAll(pageable).map(DadosListagemMedico::new);
    }
}
