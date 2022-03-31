package com.mbc.clickclinic.dao;

import com.mbc.clickclinic.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
}
