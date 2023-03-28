package com.raphaelfontoura.medvoll.api.domain.medico;

import com.raphaelfontoura.medvoll.api.domain.endereco.DadosEndereco;
import jakarta.validation.constraints.NotNull;

public record DadosAtualizaMedico(
        @NotNull
        Long id,
        String nome,
        String telefone,
        DadosEndereco endereco
) {
}
