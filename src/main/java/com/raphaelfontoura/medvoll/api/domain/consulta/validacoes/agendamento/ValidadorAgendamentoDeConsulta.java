package com.raphaelfontoura.medvoll.api.domain.consulta.validacoes.agendamento;

import com.raphaelfontoura.medvoll.api.domain.consulta.DadosAgendamentoConsulta;

public interface ValidadorAgendamentoDeConsulta {
    void validar(DadosAgendamentoConsulta dados);
}
