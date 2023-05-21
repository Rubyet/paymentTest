package com.testpro.payment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testpro.payment.entity.Payment;
import com.testpro.payment.repository.iPaymentRepo;

@RestController
@RequestMapping("/api")
public class PaymentController {
    
    @GetMapping("/health")
    public String showApiStatus(){
        return "ok";
    }

    @Autowired
    iPaymentRepo paymentRepo;

    @PostMapping("/createpayment")
    public ResponseEntity<Payment> save(@RequestBody Payment payment){
        try {
            return new ResponseEntity<Payment>(paymentRepo.save(payment),HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<Payment>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
