package com.stepuro.money.transaction.web;

import com.stepuro.money.transaction.storage.service.TransactionStorageService;
import com.stepuro.money.transaction.web.dto.TransactionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/user/{userId}")
    public List<TransactionDto> getByUserId(
            @PathVariable final UUID userId,
            @RequestParam(required = false) final Integer pageSize,
            @RequestParam(required = false) final Integer pageNumber
    ) {
        return storageService.findTransactions(userId, pageSize, pageNumber);
    }

    @PostMapping
    public TransactionDto saveTransaction(@RequestBody final TransactionDto transaction) {
        return storageService.saveNewTransaction(transaction);
    }

}
