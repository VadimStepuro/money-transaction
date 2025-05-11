package com.stepuro.money.transaction.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.RoundingMode;

@ConfigurationProperties("conversion")
public record ConversionProperties(
        String key,
        String defaultCurrency,
        Integer scale,
        RoundingMode roundingMode
) {
}
