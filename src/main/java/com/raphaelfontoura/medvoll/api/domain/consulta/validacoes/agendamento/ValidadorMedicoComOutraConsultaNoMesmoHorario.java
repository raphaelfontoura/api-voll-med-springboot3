package com.raphaelfontoura.medvoll.api.domain.consulta.validacoes.agendamento;

import com.raphaelfontoura.medvoll.api.domain.consulta.ConsultaRepository;
import com.raphaelfontoura.medvoll.api.domain.consulta.DadosAgendamentoConsulta;
import com.raphaelfontoura.medvoll.api.domain.exception.ValidacaoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private ConsultaRepository repository;

    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        var medicoPossuiOutraConsulta = repository.existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(dados.idMedico(), dados.data());
        if (medicoPossuiOutraConsulta) {
            throw new ValidacaoException("Médico já possui outra consulta agendada nesse mesmo horário.");
        }
    }
}
