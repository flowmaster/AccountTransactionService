/**
 * This is transaction based micro-service initiation class.
 * @author Sambed
 * @date 14/07/2019
 * @date last update - 
 * @change by - 
 */
package com.monese.account.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TransactionServiceInitiation {

	public static void main(String[] args) {
		SpringApplication.run(TransactionServiceInitiation.class, args);
	}
}
