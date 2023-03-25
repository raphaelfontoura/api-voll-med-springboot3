package com.raphaelfontoura.medvoll.api.medico;

import com.raphaelfontoura.medvoll.api.endereco.DadosEndereco;

public record DadosCadastroMedico(
        String nome,
        String email,
        String crm,
        Especialidade especialidade,
        DadosEndereco endereco
) {
}
