package com.br.tuaobra.utils.exceptions;

public class CasaConstrucaoNaoEncontradaException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public CasaConstrucaoNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public CasaConstrucaoNaoEncontradaException(String msg, Throwable causa) {
		super(msg, causa);
	}
	

}
