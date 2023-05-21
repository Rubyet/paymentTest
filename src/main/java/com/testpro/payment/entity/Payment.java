package com.testpro.payment.entity;

import java.sql.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.boot.registry.selector.spi.DialectSelector;

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

    @Column(nullable = false)
    private String cusomerName;

    @Column(nullable = false)
    private Float Ammount;

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    private PayMethod method;

    @Column(nullable = false)
    private Status staus;

    @CreationTimestamp
    @Column(name = "created_at",nullable = false,updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(name = "updaed_at",nullable = false)
    private Date updatedAt;

    public enum PayMethod{
        CARD,BANKTRANSFER,PAYPAL,OTHERS
    }

    public enum Status{
        OPEN,PROCESSING,APPROVED,REJECTED
    }

    public PayMethod getMethod(){
        return this.method;
    }

    public Status setStatus(Status status){
        return this.staus = status;
    }
}
