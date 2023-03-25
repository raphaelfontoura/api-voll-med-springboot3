package com.raphaelfontoura.medvoll.api.endereco;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {
    private String logradouro;
    private String bairro;
    private String cep;
    private String numero;
    private String complemento;
    private String cidade;
    private String uf;

    public Endereco(DadosEndereco endereco) {
        logradouro = endereco.logradouro();
        bairro = endereco.bairro();
        cep = endereco.cep();
        numero = endereco.numero();
        complemento = endereco.complemento();
        cidade = endereco.cidade();
        uf = endereco.uf();
    }

    public void atualizarInformacoes(DadosEndereco endereco) {
        if (endereco.logradouro() != null) logradouro = endereco.logradouro();
        if (endereco.bairro() != null) bairro = endereco.bairro();
        if (endereco.cep() != null) cep = endereco.cep();
        if (endereco.numero() != null) numero = endereco.numero();
        if (endereco.complemento() != null) complemento = endereco.complemento();
        if (endereco.cidade() != null) cidade = endereco.cidade();
        if (endereco.uf() != null) uf = endereco.uf();
    }
}
