package com.raphaelfontoura.medvoll.api.domain.consulta.validacoes;

import com.raphaelfontoura.medvoll.api.domain.consulta.DadosAgendamentoConsulta;
import com.raphaelfontoura.medvoll.api.domain.exception.ValidacaoException;
import com.raphaelfontoura.medvoll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta {

    @Autowired
    private PacienteRepository repository;

    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        var pacienteEstaAtivo = repository.findAtivoById(dados.idPaciente());
        if (!pacienteEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com paciente excluído.");
        }
    }
}
