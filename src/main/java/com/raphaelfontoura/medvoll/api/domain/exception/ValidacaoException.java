package com.raphaelfontoura.medvoll.api.domain.exception;

public class ValidacaoException extends RuntimeException{
    public ValidacaoException(String mensagem){
        super(mensagem);
    }
}
