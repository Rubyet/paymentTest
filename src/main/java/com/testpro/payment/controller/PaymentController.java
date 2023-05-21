package com.testpro.payment.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.testpro.payment.entity.Payment;
import com.testpro.payment.entity.Payment.PayMethod;
import com.testpro.payment.entity.Payment.Status;
import com.testpro.payment.repository.iPaymentRepo;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {
    
    @GetMapping("/health")
    public String showApiStatus(){
        return "ok";
    }

    @Autowired
    iPaymentRepo paymentRepo;

    @GetMapping("/list")
	public ResponseEntity<List<Payment>> getAllPayments() {
		try {
			List<Payment> list = paymentRepo.findAll();
			
			if (list.isEmpty() || list.size() == 0) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			return new ResponseEntity<>(list, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    /**
     * @param payment
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<Payment> savePayment(@RequestBody Payment payment){
        try {
            payment.setStatus(Status.OPEN);
            if(payment.getMethod() == PayMethod.CARD)
            {
                return new ResponseEntity<Payment>(paymentRepo.save(payment),HttpStatus.CREATED);
            }
            else
            {
                return new ResponseEntity<Payment>(HttpStatus.NOT_ACCEPTABLE);
            }
            // System.out.println();
        } catch (Exception e) {
            return new ResponseEntity<Payment>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Payment> getPayment(@PathVariable Integer id) {
		Optional<Payment> payment = paymentRepo.findById(id);
		
		if (payment.isPresent()) {
			return new ResponseEntity<>(payment.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/")
	public ResponseEntity<Payment> updatePayment(@RequestBody Payment payment) {
		try {
			return new ResponseEntity<>(paymentRepo.save(payment), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deletePayment(@PathVariable Integer id) {
		try {
			Optional<Payment> payment = paymentRepo.findById(id);
			if (payment.isPresent()) {
				paymentRepo.delete(payment.get());
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
            else{
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
