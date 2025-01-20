package com.br.tuaobra.utils.exceptions;

public class DemandaNaoEncontradaException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public DemandaNaoEncontradaException(String msg) {
		super(msg);
	}
	
	public DemandaNaoEncontradaException(String msg, Throwable causa) {
		super(msg, causa);
	}
	

}
