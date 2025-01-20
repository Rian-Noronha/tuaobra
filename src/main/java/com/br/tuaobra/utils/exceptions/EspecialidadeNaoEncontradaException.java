package com.br.tuaobra.utils.exceptions;

public class EspecialidadeNaoEncontradaException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public EspecialidadeNaoEncontradaException(String msg) {
		super(msg);
	}
	
	 public EspecialidadeNaoEncontradaException(String mensagem, Throwable causa) {
	        super(mensagem, causa);
    }
	
	
}
