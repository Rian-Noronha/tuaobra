package com.br.tuaobra.utils.exceptions;

public class ClienteNaoEncontradoException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public ClienteNaoEncontradoException(String msg) {
		super(msg);
	}
	
	public ClienteNaoEncontradoException(String msg, Throwable causa) {
		super(msg, causa);
	}
	

}
