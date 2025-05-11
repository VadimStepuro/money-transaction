package com.stepuro.money.transaction.service;

import com.stepuro.money.transaction.client.ConversionClient;
import com.stepuro.money.transaction.config.ConversionProperties;
import com.stepuro.money.transaction.model.Conversion;
import com.stepuro.money.transaction.model.ConversionHistoricalResponse;
import com.stepuro.money.transaction.storage.service.TransactionStorageService;
import com.stepuro.money.transaction.web.dto.TransactionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
@EnableConfigurationProperties(ConversionProperties.class)
public class ConversionServiceImpl implements ConversionService {

    private final ConversionProperties properties;
    private final TransactionStorageService transactionStorageService;
    private final ConversionClient client;

    @Override
    public Map<String, Conversion> getConversion(
            final LocalDate date,
            final List<String> currencies,
            final String baseCurrency
    ) {
        return client.getHistoricalData(
                properties.key(),
                        date,
                        Strings.isBlank(baseCurrency) ? properties.defaultCurrency() : baseCurrency)
                .getData()
                .entrySet()
                .stream()
                .filter(entry -> currencies.contains(entry.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    @Override
    public List<TransactionDto> findTransactions(
            final UUID userId,
            final Integer pageSize,
            final Integer pageNumber,
            final String currency
    ) {
        final List<TransactionDto> transactions = transactionStorageService.findTransactions(
                userId,
                pageSize,
                pageNumber
        );

        return transactions.stream()
                .map(transaction -> {

                    if (currency.equals(transaction.getCurrencyCode())) {
                        return transaction;
                    }

                    final ConversionHistoricalResponse historicalData = client.getHistoricalData(
                            properties.key(),
                            transaction.getDate().toLocalDate(),
                            properties.defaultCurrency()
                    );

                    final Map<String, Conversion> currencyRate = historicalData.getData();
                    final Conversion baseConversion = currencyRate.get(transaction.getCurrencyCode());
                    final Conversion mainConversion = currencyRate.get(currency);

                    if (baseConversion != null) {
                        transaction.setAmount(transaction.getAmount()
                                .divide(baseConversion.getValue(), properties.scale(), properties.roundingMode()));
                        }

                    transaction.setAmount(transaction.getAmount()
                            .multiply(mainConversion.getValue())
                            .setScale(properties.scale(), properties.roundingMode()));
                    transaction.setCurrencyCode(currency);

                    return transaction;
                }).toList();
    }
}
