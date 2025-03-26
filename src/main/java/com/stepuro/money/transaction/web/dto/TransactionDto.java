package com.stepuro.money.transaction.web.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionDto {
    @NotNull
    private UUID id;
    private UUID userId;
    @NotNull
    @Positive
    private BigDecimal amount;
    @NotNull
    private TransactionType type;
    private String cardMask;
    private LocalDateTime date;
    private String currencyCode;
    private SalesPointDto salesPoint;
    private GroupDto group;
}
