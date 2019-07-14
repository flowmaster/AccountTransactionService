package com.monese.account.transaction.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.monese.account.transaction.exception.AccountNotFoundException;
import com.monese.account.transaction.exception.MinimumAmtTransactionException;
import com.monese.account.transaction.exception.NotEnoughFundsException;
import com.monese.account.transaction.exception.TransferBetweenSameAccountException;
import com.monese.account.transaction.model.Account;
import com.monese.account.transaction.model.TransferDetail;

@Component
public class TransferValidatorImpl implements TransferValidator {

	/**
	 * Validate whether the accounts exist along with money transfer cannot happen between same account and
	 * there would be enough fund to complete the transfer.
	 * Transfer amount can not be zero or less.
	 *
	 * @param currAccountFrom 
	 * @param currAccountTo 
	 * @param transfer
	 * @throws AccountNotFoundException
	 * @throws NotEnoughFundsException
	 * @throws TransferBetweenSameAccountException
	 * @throws MinimumAmtTransactionException
	 */
	public void validate(final Account currAccountFrom, final Account currAccountTo, final TransferDetail transfer)
			throws AccountNotFoundException, NotEnoughFundsException, TransferBetweenSameAccountException, MinimumAmtTransactionException{

		if (currAccountFrom == null){
			throw new AccountNotFoundException("Account " + transfer.getSrcAccountId() + " not found.");
		}

		if (currAccountTo == null) {
			throw new AccountNotFoundException("Account " + transfer.getDstAccountId() + " not found.");
		}

		if (sameAccount(transfer)){
			throw new TransferBetweenSameAccountException("Transfer to self not permitted.");
		}

		if (!enoughFunds(currAccountFrom, transfer.getAmount())){
			throw new NotEnoughFundsException("Not enough funds on account " + currAccountFrom.getAccountId() + " balance="+currAccountFrom.getBalance());
		}
		if (transfer.getAmount().compareTo(BigDecimal.ZERO) <= 0){
			throw new MinimumAmtTransactionException("The minumum tranfer amount should be more than zero " );
		}
	}

	private boolean sameAccount(final TransferDetail transfer) {
		return transfer.getSrcAccountId().equals(transfer.getDstAccountId());
	}


	private boolean enoughFunds(final Account account, final BigDecimal amount) {
		return account.getBalance().subtract(amount).compareTo(BigDecimal.ZERO) >= 0;
	}

}
