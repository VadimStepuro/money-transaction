package com.stepuro.money.transaction.service;

import com.stepuro.money.transaction.model.Conversion;
import com.stepuro.money.transaction.web.dto.TransactionDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ConversionService {
    Map<String, Conversion> getConversion(
            LocalDate date,
            List<String> currencies,
            String baseCurrency,
            String preferredCurrency
    );

    List<TransactionDto> findTransactions(
            UUID userId,
            Integer pageSize,
            Integer pageNumber,
            String currency,
            String preferredCurrency,
            UUID groupId,
            LocalDate startDate,
            LocalDate endDate
    );
}
