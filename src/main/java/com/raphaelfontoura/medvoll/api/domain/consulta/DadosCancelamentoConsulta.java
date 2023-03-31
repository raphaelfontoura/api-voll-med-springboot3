package com.raphaelfontoura.medvoll.api.domain.consulta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoConsulta(
        Long idConsulta,
        @NotNull
        MotivoCancelamento motivo
) {
}
