package com.br.tuaobra.utils.exceptions;

public class CamposNaoValidadosException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public CamposNaoValidadosException(String msg) {
		super(msg);
	}
	
	public CamposNaoValidadosException(String msg, Throwable causa) {
		super(msg, causa);
	}
	
	

}
