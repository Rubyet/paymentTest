package com.testpro.payment.entity;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Table(name="tbl_payment")
@Entity
@Setter
@Getter
@ToString
public class Payment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String cusomerName;

    private Float Ammount;

    @Enumerated(EnumType.ORDINAL)
    private PayMethod method;

    private Status staus;

    @CreationTimestamp
    @Column(name = "created_at")
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updaed_at")
    private Date updatedAt;

    public enum PayMethod{
        CARD,BANKTRANSFER,PAYPAL,OTHERS
    }

    public enum Status{
        OPEN,PROCESSING,APPROVED,REJECTED
    }
}
