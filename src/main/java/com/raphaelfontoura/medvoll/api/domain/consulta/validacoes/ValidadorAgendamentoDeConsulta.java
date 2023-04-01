package com.raphaelfontoura.medvoll.api.domain.consulta.validacoes;

import com.raphaelfontoura.medvoll.api.domain.consulta.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoDeConsulta {
    void validar(DadosAgendamentoConsulta dados);
}
