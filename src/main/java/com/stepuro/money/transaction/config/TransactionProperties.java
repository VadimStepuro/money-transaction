package com.stepuro.money.transaction.config;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties("transaction")
public class TransactionProperties {
    private final int defaultPageSize;
    private final int defaultPageNumber;
}
