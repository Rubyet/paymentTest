package com.testpro.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testpro.payment.entity.Payment;

@Repository
public interface iPaymentRepo extends JpaRepository<Payment, Integer> {
    
}
