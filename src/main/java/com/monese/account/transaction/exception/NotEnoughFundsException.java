package com.monese.account.transaction.exception;

public class NotEnoughFundsException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotEnoughFundsException(String message){
        super(message);
    }
}