package com.cesar31.transaction.infrastructure.adapters.output.persistence.repository;

import com.cesar31.transaction.infrastructure.adapters.output.persistence.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PaymentEntityRepository extends JpaRepository<PaymentEntity, UUID> {
}
