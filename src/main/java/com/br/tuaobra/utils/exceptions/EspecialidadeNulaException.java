package com.br.tuaobra.utils.exceptions;

public class EspecialidadeNulaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EspecialidadeNulaException(String msg) {
		super(msg);
	}

	public EspecialidadeNulaException(String msg, Throwable causa) {
		super(msg, causa);
	}

}
