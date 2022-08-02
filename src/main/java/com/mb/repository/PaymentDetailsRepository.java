package com.mb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mb.entity.PaymentDetails;

public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails, Long>
{

}
