package com.raphaelfontoura.medvoll.api.paciente;

import com.raphaelfontoura.medvoll.api.endereco.DadosEndereco;
import com.raphaelfontoura.medvoll.api.endereco.Endereco;

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
