package com.raphaelfontoura.medvoll.api.domain.consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    Boolean existsByMedicoIdAndDataAndMotivoCancelamentoIsNull(Long id, LocalDateTime data);

    Boolean existsByPacienteIdAndDataBetween(Long id, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);
}
