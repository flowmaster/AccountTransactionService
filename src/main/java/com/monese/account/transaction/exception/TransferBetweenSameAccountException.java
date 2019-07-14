package com.monese.account.transaction.exception;

public class TransferBetweenSameAccountException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TransferBetweenSameAccountException(String message){
        super(message);
    }

}