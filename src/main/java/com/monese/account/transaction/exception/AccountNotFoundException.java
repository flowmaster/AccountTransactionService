/**
 * Customer exception while Account not found in the Account master.
 * @author Sambed
 * @date 14/07/2019
 * @date last update - 
 * @change by - 
 */
package com.monese.account.transaction.exception;

public class AccountNotFoundException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AccountNotFoundException(String message) {
    	super(message);
        
    }

}
