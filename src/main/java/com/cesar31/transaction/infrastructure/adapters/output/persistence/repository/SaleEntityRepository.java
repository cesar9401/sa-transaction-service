package com.cesar31.transaction.infrastructure.adapters.output.persistence.repository;

import com.cesar31.transaction.infrastructure.adapters.output.persistence.entity.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SaleEntityRepository extends JpaRepository<SaleEntity, UUID> {
}
