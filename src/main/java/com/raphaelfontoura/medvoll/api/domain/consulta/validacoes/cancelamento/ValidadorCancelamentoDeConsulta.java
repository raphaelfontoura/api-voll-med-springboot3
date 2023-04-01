package com.raphaelfontoura.medvoll.api.domain.consulta.validacoes.cancelamento;

import com.raphaelfontoura.medvoll.api.domain.consulta.DadosCancelamentoConsulta;

public interface ValidadorCancelamentoDeConsulta {
    void validar(DadosCancelamentoConsulta dados);
}
