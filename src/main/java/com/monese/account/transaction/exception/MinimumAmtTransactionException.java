package com.monese.account.transaction.exception;

public class MinimumAmtTransactionException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MinimumAmtTransactionException(String message){
        super(message);
    }

}
