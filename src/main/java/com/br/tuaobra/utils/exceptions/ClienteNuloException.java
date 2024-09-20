package com.br.tuaobra.utils.exceptions;

public class ClienteNuloException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ClienteNuloException(String msg) {
		super(msg);
	}

	public ClienteNuloException(String msg, Throwable causa) {
		super(msg, causa);
	}

}
