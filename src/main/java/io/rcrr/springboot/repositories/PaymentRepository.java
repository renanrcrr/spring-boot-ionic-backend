package io.rcrr.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.rcrr.springboot.domains.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer>{

}
