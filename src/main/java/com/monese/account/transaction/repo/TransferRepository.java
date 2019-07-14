/**
 * Repository for the amount transfer transaction dear with TRANSFER entity.  
 * @author Sambed
 * @date 14/07/2019
 * @date last update - 
 * @change by - 
 */
package com.monese.account.transaction.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.monese.account.transaction.model.TransferDetail;

public interface TransferRepository extends JpaRepository<TransferDetail, String>{

}
