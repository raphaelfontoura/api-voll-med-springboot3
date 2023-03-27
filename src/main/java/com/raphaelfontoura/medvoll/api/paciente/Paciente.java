package com.raphaelfontoura.medvoll.api.paciente;

import com.raphaelfontoura.medvoll.api.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pacientes")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Paciente {
    @Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;
    @Embedded
    private Endereco endereco;

    public Paciente(DadosCadastroPaciente dadosPaciente) {
        nome = dadosPaciente.nome();
        email = dadosPaciente.email();
        telefone = dadosPaciente.telefone();
        cpf = dadosPaciente.cpf();
        endereco = new Endereco(dadosPaciente.endereco());
    }

    public void atualizaDados(DadosAtualizaPaciente dados) {
        if (dados.nome() != null && ! dados.nome().isBlank()) nome = dados.nome();
        if (dados.telefone() != null) telefone = dados.telefone();
        if (dados.endereco() != null) endereco.atualizarInformacoes(dados.endereco());
    }
}
