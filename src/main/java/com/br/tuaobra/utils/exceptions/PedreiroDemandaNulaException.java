package com.br.tuaobra.utils.exceptions;

public class PedreiroDemandaNulaException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public PedreiroDemandaNulaException(String msg) {
		super(msg);
	}
	
	public PedreiroDemandaNulaException(String msg, Throwable causa) {
		super(msg, causa);
	}

}
