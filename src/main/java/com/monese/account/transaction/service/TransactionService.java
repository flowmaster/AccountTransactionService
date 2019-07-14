/**
 * A service which receives the account related transaction from controller .  
 * @author Sambed
 * @date 14/07/2019
 * @date last update - 
 * @change by - 
 */

package com.monese.account.transaction.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.monese.account.transaction.exception.AccountNotFoundException;
import com.monese.account.transaction.model.Account;
import com.monese.account.transaction.model.Statement;
import com.monese.account.transaction.model.Transaction;
import com.monese.account.transaction.model.TransferDetail;
import com.monese.account.transaction.repo.AccountRepository;
import com.monese.account.transaction.repo.TransferRepository;

@Service
public class TransactionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionService.class);

	/*
	 * Account repository injection
	 */
	@Autowired
	AccountRepository accountsRepository;

	/*
	 * Transaction repository injection
	 */
	@Autowired
	TransferRepository transferRepository;

	/*
	 * A validator to perform validation on the different transaction
	 */
	@Autowired
	private TransferValidator transferValidator;


	/**
	 * Provides statement for customer which are already registered specified by the {@link accountId} object
	 * @param accountId
	 * @return
	 */
	public Statement statement(String accountId){
		return accountsRepository.findById(accountId)
				.filter(a -> a.getAccountId() != null)
				.map(a -> new ModelMapper().map(a, Statement.class))
				.orElseThrow(() -> new AccountNotFoundException("Account " + accountId + " not found."));
	}

	/**
	 * Makes a transfer between two accounts for the balance specified by the {@link Transfer} object
	 * @param transfer
	 * @throws AccountNotFoundException When an account does not exist
	 */
	public void makeTransfer(TransferDetail transfer) throws AccountNotFoundException {

		final Account accountFrom = accountsRepository.findById(transfer.getSrcAccountId()).map( x-> x)
				.orElseThrow(() -> new AccountNotFoundException("Source Account " + transfer.getSrcAccountId() + " not found."));
		final Account accountTo = accountsRepository.findById(transfer.getDstAccountId()).map( x-> x)
				.orElseThrow(() -> new AccountNotFoundException("Destinition Account " + transfer.getDstAccountId() + " not found."));
		final BigDecimal amount = transfer.getAmount();
		LOGGER.debug("Tranfer amount = " +amount);
		transferValidator.validate(accountFrom, accountTo, transfer);
		transfer.setTransTime(new Date());
		transfer.setTransSuccess("Y");
		transferRepository.save(transfer);
		boolean successful = accountsRepository.updateAccountsBatch(Arrays.asList(
				new Account(accountFrom.getAccountId(), amount.negate().add(accountFrom.getBalance()),accountFrom.getCustomer(),new Transaction(amount,new Date() ,"DR")),
				new Account(accountTo.getAccountId(), amount.add(accountTo.getBalance()),accountFrom.getCustomer(),new Transaction(amount,new Date() ,"CR"))
				));

		if (!successful){
			LOGGER.error("Balance tranfer failure and make transaction as failure");
			transfer.setTransSuccess("N");
			transferRepository.save(transfer);
		}
	}

}
