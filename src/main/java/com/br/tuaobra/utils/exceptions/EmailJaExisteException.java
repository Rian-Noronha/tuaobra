package com.br.tuaobra.utils.exceptions;

public class EmailJaExisteException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	 public EmailJaExisteException(String mensagem) {
	        super(mensagem);
	    }

	    public EmailJaExisteException(String mensagem, Throwable causa) {
	        super(mensagem, causa);
	    }
}
