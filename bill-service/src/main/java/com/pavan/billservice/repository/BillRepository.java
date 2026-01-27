package com.pavan.billservice.repository;

import com.pavan.billservice.entity.Bill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BillRepository extends JpaRepository<Bill, Long> {

    List<Bill> findByUserId(UUID userId);

    Optional<Bill> findByIdAndUserId(Long id, UUID userId);
}

