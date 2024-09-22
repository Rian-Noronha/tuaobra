package com.br.tuaobra.utils.exceptions;

public class CasaConstrucaoNulaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CasaConstrucaoNulaException(String msg) {
		super(msg);
	}

	public CasaConstrucaoNulaException(String msg, Throwable causa) {
		super(msg, causa);
	}

}
