package com.stepuro.money.transaction.storage.repository;

import com.stepuro.money.transaction.storage.entity.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findAllByUserIdOrderByDateDesc(UUID userId, Pageable pageable);

    List<Transaction> findAllByGroupIdAndUserIdOrderByDateDesc(UUID groupId, UUID userId, Pageable pageable);

    List<Transaction> findAllByDateBetweenAndUserIdOrderByDateDesc(
            LocalDateTime startDate,
            LocalDateTime endDate,
            UUID userId,
            Pageable pageable
    );

    List<Transaction> findAllByDateBetweenAndGroupIdAndUserIdOrderByDateDesc(
            LocalDateTime startDate,
            LocalDateTime endDate,
            UUID groupId,
            UUID userId,
            Pageable pageable
    );
}
