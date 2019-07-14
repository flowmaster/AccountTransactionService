/**
 * Repository to deals with Accounts which is master table and have reference to Transaction entity 
 * in a one to many relation.  
 * @author Sambed
 * @date 14/07/2019
 * @date last update - 
 * @change by - 
 */
package com.monese.account.transaction.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.monese.account.transaction.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String>{

	/**
	 * Perform the job in an atomic nature.
	 * @param accountUpdates
	 * @return
	 */
	default public boolean updateAccountsBatch(List<Account> accountUpdates) {
		accountUpdates
		.stream()
		.forEach(this::updateAccount);
		return true;
	}

	/**
	 * Update the account with new transaction value
	 * @param accountUpdate
	 */
	default void updateAccount(Account accountUpdate)
	{
		save(accountUpdate);
	} 
}
