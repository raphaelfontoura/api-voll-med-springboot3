package com.raphaelfontoura.medvoll.api.domain.paciente;

import com.raphaelfontoura.medvoll.api.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizaPaciente(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco
) {
}
