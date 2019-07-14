/**
 * A transaction based REST interface which exposes the endpoint for different operations on the account.
 * @author Sambed
 * @date 14/07/2019
 * @date last update - 
 * @change by - 
 */

package com.monese.account.transaction.controller;


import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.monese.account.transaction.model.Statement;
import com.monese.account.transaction.model.TransferDetail;
import com.monese.account.transaction.service.TransactionService;


@RestController
@RequestMapping("/accounts")
@Transactional
public class TransactionController {

	/*
	 * Inject the service object.
	 */
	@Autowired
	TransactionService transService;


	/**
	 * Expose statement end point with a param value as the accountId and offers the client a JSON having related
	 * statement elements /detail.
	 * @param accountId
	 * @return
	 */
	@ApiOperation(value = "Retrieve the Statement for the account")
	@GetMapping("/{accountId}/statement")
	public ResponseEntity<Statement> accountStatement(@PathVariable String accountId) {
		Statement acct = transService.statement(accountId);
		return ResponseEntity.ok().body(acct);
	}

	/**
	 * Exposes an endpoint to make a transfer transaction between two accounts having enough fund to obtain it.
	 * @param transfer
	 * @return
	 */
	@ApiOperation(value = "Make a transfer transaction between two different account")
	@PostMapping(path = "/transfer", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> makeTransfer(@RequestBody TransferDetail transfer) {

		this.transService.makeTransfer(transfer);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
