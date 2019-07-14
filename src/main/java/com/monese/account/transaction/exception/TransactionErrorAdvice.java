/**
 * An response exception filter which handle all the given exception and responds to client with very 
 * informative error description along with http code.
 * @author Sambed
 * @date 14/07/2019
 * @date last update - 
 * @change by - 
 */
package com.monese.account.transaction.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TransactionErrorAdvice extends ResponseEntityExceptionHandler {

	/**
	 *Handle for account not found . 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(AccountNotFoundException.class)
	public final ResponseEntity<ErrorResponse> handleUserNotFoundException(AccountNotFoundException ex, WebRequest request) {
		return error(ex,request,HttpStatus.NOT_FOUND);
	}

	/**
	 *	Handle while source account does not have enough money which being transfer to destinition one. 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(NotEnoughFundsException.class)
	public final ResponseEntity<ErrorResponse> handleNotEnoughFundsException(NotEnoughFundsException ex, WebRequest request) {
		return error(ex,request,HttpStatus.NOT_ACCEPTABLE);
	}

	/**
	 *Handle while the transaction being occur within the same account. 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(TransferBetweenSameAccountException.class)
	public final ResponseEntity<ErrorResponse> handleTransferBetweenSameAccountException(TransferBetweenSameAccountException ex, WebRequest request) {
		return error(ex,request,HttpStatus.NOT_ACCEPTABLE);
	}

	/**
	 *Handle while a transfer going to happen have negative value or zero.
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler(MinimumAmtTransactionException.class)
	public final ResponseEntity<ErrorResponse> handleMinimumAmtTransactionException(MinimumAmtTransactionException ex, WebRequest request) {
		return error(ex,request,HttpStatus.NOT_ACCEPTABLE);
	}

	/**
	 * A generic error handle method to frame the error response
	 * @param ex
	 * @param request
	 * @param status
	 * @return
	 */
	private final ResponseEntity<ErrorResponse> error(Exception ex, WebRequest request , HttpStatus status) {
		ErrorResponse errorDetails = new ErrorResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(errorDetails, status);
	}
}
