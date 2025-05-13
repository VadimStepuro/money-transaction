package com.stepuro.money.transaction.web;

import com.stepuro.money.transaction.model.Conversion;
import com.stepuro.money.transaction.service.ConversionService;
import com.stepuro.money.transaction.web.dto.TransactionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(
        value = "/api/v1/transactions/conversions",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class ConversionController {

    private final ConversionService conversionService;

    @GetMapping("user")
    public List<TransactionDto> getByUserId(
            @RequestHeader("X-USER-ID") final UUID userId,
            @RequestHeader("X-PREFERRED-CURRENCY") final String preferredCurrency,
            @RequestParam(required = false) final Integer pageSize,
            @RequestParam(required = false) final Integer pageNumber,
            @RequestParam final String currency,
            @RequestParam(required = false) final UUID groupId,
            @RequestParam(required = false) final LocalDate startDate,
            @RequestParam(required = false) final LocalDate endDate
    ) {
        return conversionService.findTransactions(
                userId,
                pageSize,
                pageNumber,
                currency,
                preferredCurrency,
                groupId,
                startDate,
                endDate
        );
    }

    @GetMapping
    public Map<String, Conversion> getConversions(
            @RequestHeader("X-PREFERRED-CURRENCY") final String preferredCurrency,
            @RequestParam final LocalDate date,
            @RequestParam final List<String> currencies,
            @RequestParam(required = false) final String baseCurrency
    ) {
        return conversionService.getConversion(date, currencies, baseCurrency, preferredCurrency);
    }
}
