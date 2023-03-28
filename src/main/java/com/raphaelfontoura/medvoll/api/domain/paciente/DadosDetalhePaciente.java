package com.raphaelfontoura.medvoll.api.domain.paciente;

import com.raphaelfontoura.medvoll.api.domain.endereco.Endereco;

public record DadosDetalhePaciente(
        Long id,
        String nome,
        String email,
        String telefone,
        String cpf,
        Endereco endereco
) {
    public DadosDetalhePaciente(Paciente paciente) {
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
    }
}
