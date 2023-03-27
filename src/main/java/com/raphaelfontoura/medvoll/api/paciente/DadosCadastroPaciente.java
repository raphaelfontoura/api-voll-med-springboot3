package com.raphaelfontoura.medvoll.api.paciente;

import com.raphaelfontoura.medvoll.api.endereco.DadosEndereco;
import com.raphaelfontoura.medvoll.api.endereco.Endereco;
import jakarta.persistence.Embedded;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

public record DadosCadastroPaciente (
    @NotBlank
    String nome,
    @Email
    @NotNull
    String email,
    @NotBlank
    String telefone,
    @NotBlank
    @CPF
    String cpf,
    @Valid
    @NotNull
    DadosEndereco endereco
) {}
