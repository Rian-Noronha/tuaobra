package com.br.tuaobra.utils.exceptions;

public class DemandaNulaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DemandaNulaException(String msg) {
		super(msg);
	}

	public DemandaNulaException(String msg, Throwable causa) {
		super(msg, causa);
	}

}
