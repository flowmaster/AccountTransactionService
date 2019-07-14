/**
 * @author Sambed
 * @date 14/07/2019
 * @date last update - 
 * @change by - 
 */
package com.monese.account.transaction.service;

import com.monese.account.transaction.exception.AccountNotFoundException;
import com.monese.account.transaction.exception.MinimumAmtTransactionException;
import com.monese.account.transaction.exception.NotEnoughFundsException;
import com.monese.account.transaction.exception.TransferBetweenSameAccountException;
import com.monese.account.transaction.model.Account;
import com.monese.account.transaction.model.TransferDetail;

public interface TransferValidator {
	
	/**
	 * Validate method for transaction statement and transfer of amount .
	 * @param accountFrom
	 * @param AccountTo
	 * @param transfer
	 * @throws AccountNotFoundException
	 * @throws NotEnoughFundsException
	 * @throws DuplicateAccountIdException
	 * @throws TransferBetweenSameAccountException
	 * @throws MinimumAmtTransactionException
	 */
	void validate(final Account accountFrom,final Account AccountTo,final TransferDetail transfer) throws AccountNotFoundException, NotEnoughFundsException, TransferBetweenSameAccountException, MinimumAmtTransactionException;
}
