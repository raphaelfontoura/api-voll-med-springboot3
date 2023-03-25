package com.raphaelfontoura.medvoll.api.medico;

import com.raphaelfontoura.medvoll.api.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "medicos")
@Entity(name = "Medico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;
    @Embedded
    private Endereco endereco;

    public Medico(DadosCadastroMedico dados) {
        nome = dados.nome();
        email = dados.email();
        telefone = dados.telefone();
        crm = dados.crm();
        especialidade = dados.especialidade();
        endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(DadosAtualizaMedico dados) {
        if (dados.nome() != null) nome = dados.nome();
        if (dados.telefone() != null) telefone = dados.telefone();
        if (dados.endereco() != null) endereco.atualizarInformacoes(dados.endereco());
    }
}
