package com.br.tuaobra.utils.exceptions;

public class PedreiroDemandaNaoEncontradaException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public PedreiroDemandaNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public PedreiroDemandaNaoEncontradaException(String msg, Throwable causa) {
		super(msg, causa);
	}

}
