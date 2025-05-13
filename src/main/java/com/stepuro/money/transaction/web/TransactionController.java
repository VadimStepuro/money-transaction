package com.stepuro.money.transaction.web;

import com.stepuro.money.transaction.storage.service.TransactionStorageService;
import com.stepuro.money.transaction.web.dto.TransactionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(
        value = "/api/v1/transactions",
        produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionStorageService storageService;

    @GetMapping("/user")
    public List<TransactionDto> getByUserId(
            @RequestHeader("X-USER-ID") final UUID userId,
            @RequestParam(required = false) final Integer pageSize,
            @RequestParam(required = false) final Integer pageNumber,
            @RequestParam(required = false) final UUID groupId,
            @RequestParam(required = false) final LocalDate startDate,
            @RequestParam(required = false) final LocalDate endDate
    ) {
        return storageService.findTransactions(userId, pageSize, pageNumber, groupId, startDate, endDate);
    }

    @PostMapping
    public TransactionDto saveTransaction(
            @RequestHeader("X-USER-ID") final UUID userId,
            @RequestBody final TransactionDto transaction
    ) {
        transaction.setUserId(userId);
        return storageService.saveNewTransaction(transaction);
    }

}
