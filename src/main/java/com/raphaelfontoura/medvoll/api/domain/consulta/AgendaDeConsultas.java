package com.raphaelfontoura.medvoll.api.domain.consulta;

import com.raphaelfontoura.medvoll.api.domain.consulta.validacoes.agendamento.ValidadorAgendamentoDeConsulta;
import com.raphaelfontoura.medvoll.api.domain.consulta.validacoes.cancelamento.ValidadorCancelamentoDeConsulta;
import com.raphaelfontoura.medvoll.api.domain.exception.ValidacaoException;
import com.raphaelfontoura.medvoll.api.domain.medico.Medico;
import com.raphaelfontoura.medvoll.api.domain.medico.MedicoRepository;
import com.raphaelfontoura.medvoll.api.domain.paciente.PacienteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AgendaDeConsultas {

    private final ConsultaRepository consultaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final List<ValidadorAgendamentoDeConsulta> validadoresAgendamento;
    private final List<ValidadorCancelamentoDeConsulta> validadoresCancelamento;

    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {
        var paciente = pacienteRepository.findByIdAndAtivoTrue(dados.idPaciente())
                .orElseThrow(() -> new EntityNotFoundException("ID do paciente não existe!"));
        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("Id do médico não existe!");
        }
        validadoresAgendamento.forEach(v -> v.validar(dados));
        var medico = escolherMedico(dados);

        var consulta = new Consulta(null, medico, paciente, dados.data(), null);

        consultaRepository.save(consulta);
        return new DadosDetalhamentoConsulta(consulta);
    }

    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null) return medicoRepository.getReferenceById(dados.idMedico());
        if (dados.especialidade() == null) throw new ValidacaoException("Especialidade é obrigatória quando médico não for escolhido!");

        return medicoRepository.escolherMedicoPorEspecialidadeLivreNaData(dados.especialidade(), dados.data())
                .orElseThrow(() -> new EntityNotFoundException("Não foi localizado médico"));
    }

    public void cancelar(DadosCancelamentoConsulta dados) {
        var consulta = consultaRepository.getReferenceById(dados.idConsulta());
        validadoresCancelamento.forEach(v -> v.validar(dados));
        consulta.setMotivoCancelamento(dados.motivo());
        consultaRepository.save(consulta);
    }
}
