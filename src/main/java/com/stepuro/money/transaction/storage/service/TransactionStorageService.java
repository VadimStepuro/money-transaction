package com.stepuro.money.transaction.storage.service;

import com.stepuro.money.transaction.web.dto.TransactionDto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface TransactionStorageService {

    List<TransactionDto> findTransactions(
            UUID userId,
            Integer pageSize,
            Integer pageNumber,
            UUID groupId,
            LocalDate startDate,
            LocalDate endDate
    );

    TransactionDto saveNewTransaction(TransactionDto newDto);

}
