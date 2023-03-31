package com.raphaelfontoura.medvoll.api.domain.consulta;

import com.raphaelfontoura.medvoll.api.domain.exception.ValidacaoException;
import com.raphaelfontoura.medvoll.api.domain.medico.Medico;
import com.raphaelfontoura.medvoll.api.domain.medico.MedicoRepository;
import com.raphaelfontoura.medvoll.api.domain.paciente.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class AgendaDeConsultas {

    private final ConsultaRepository consultaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {
        validaDiaDaSemanaEHora(dados);
        var paciente = pacienteRepository.findByIdAndAtivoTrue(dados.idPaciente())
                .orElseThrow(() -> new EntityNotFoundException("ID do paciente não existe!"));
        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("Id do médico não existe!");
        }
        var medico = escolherMedico(dados);

        var consulta = new Consulta(null, medico, paciente, dados.data());

        consultaRepository.save(consulta);
        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null) return medicoRepository.getReferenceById(dados.idMedico());
        if (dados.especialidade() == null) throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido!");

        return medicoRepository.escolherMedicoPorEspecialidadeLivreNaData(dados.especialidade(), dados.data())
                .orElseThrow(() -> new EntityNotFoundException("Não foi localizado médico"));
    }

    private void validaDiaDaSemanaEHora(DadosAgendamentoConsulta dados) {
        var dayOfWeek = dados.data().getDayOfWeek();
        if (dayOfWeek == DayOfWeek.SUNDAY) {
            throw new ValidacaoException("A data da consulta não pode ser no domingo");
        }
        var localTime = LocalTime.of(dados.data().getHour(), dados.data().getMinute());
        if (localTime.isBefore(LocalTime.of(7,0)) || localTime.isAfter(LocalTime.of(19,0))) {
            throw new ValidacaoException("A hora deve ser entre 7:00 e 19:00");
        }
        if (localTime.isBefore(LocalTime.now().plusMinutes(30))) {
            throw new ValidacaoException("A consulta deve ser agendada com antecedência de 30 minutos");
        }
    }

    public void cancelar(DadosCancelamentoConsulta dados) {
        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        var dataConsulta = consulta.getData();
        if (dataConsulta.isBefore(LocalDateTime.now()) && dataConsulta.isAfter(LocalDateTime.now().plusDays(1))) {
            throw new ValidacaoException("A consulta só pode ser cancelada com antecedência mínima de 24 horas");
        }
        consultaRepository.deleteById(dados.idConsulta());
    }
}
