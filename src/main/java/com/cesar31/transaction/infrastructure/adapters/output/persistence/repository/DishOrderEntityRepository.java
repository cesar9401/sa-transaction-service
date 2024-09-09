package com.cesar31.transaction.infrastructure.adapters.output.persistence.repository;

import com.cesar31.transaction.infrastructure.adapters.output.persistence.entity.DishOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DishOrderEntityRepository extends JpaRepository<DishOrderEntity, UUID> {
}
