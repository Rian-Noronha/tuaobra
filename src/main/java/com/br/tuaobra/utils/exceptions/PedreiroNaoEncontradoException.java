package com.br.tuaobra.utils.exceptions;

public class PedreiroNaoEncontradoException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public PedreiroNaoEncontradoException(String msg) {
		super(msg);
	}
	
	 public PedreiroNaoEncontradoException(String mensagem, Throwable causa) {
	        super(mensagem, causa);
    }
	
	
}
