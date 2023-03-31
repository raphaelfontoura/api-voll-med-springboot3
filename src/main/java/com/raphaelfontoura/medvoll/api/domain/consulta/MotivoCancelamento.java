package com.raphaelfontoura.medvoll.api.domain.consulta;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MotivoCancelamento {
    PACIENTE_DESISTIU("paciente desistiu"),
    MEDICO_CANCELOU("médico cancelou"),
    OUTROS("outros");

    private final String description;

    MotivoCancelamento(String description) {
        this.description = description;
    }

    @JsonValue
    public String getDescription() {
        return description;
    }
}
