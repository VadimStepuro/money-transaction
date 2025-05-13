package com.stepuro.money.transaction.storage.service.impl;

import com.stepuro.money.transaction.config.TransactionProperties;
import com.stepuro.money.transaction.storage.entity.Transaction;
import com.stepuro.money.transaction.storage.repository.TransactionRepository;
import com.stepuro.money.transaction.storage.service.TransactionStorageService;
import com.stepuro.money.transaction.web.dto.TransactionDto;
import com.stepuro.money.transaction.web.dto.TransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@EnableConfigurationProperties({TransactionProperties.class})
public class TransactionStorageServiceImpl implements TransactionStorageService {
    private final TransactionRepository repository;
    private final TransactionProperties properties;

    @Override
    public List<TransactionDto> findTransactions(
            final UUID userId,
            final Integer pageSize,
            final Integer pageNumber,
            final UUID groupId,
            final LocalDate startDate,
            final LocalDate endDate
    ) {
        final Pageable request = getPageable(pageSize, pageNumber);

        if (groupId != null) {
            if (startDate != null && endDate != null) {
                return repository.findAllByDateBetweenAndGroupIdAndUserIdOrderByDateDesc(startDate.atStartOfDay(),
                                endDate.atStartOfDay(),
                                groupId,
                                userId,
                                request)
                        .stream()
                        .map(TransactionMapper.INSTANCE::entityToDto)
                        .toList();
            }

            return repository.findAllByGroupIdAndUserIdOrderByDateDesc(groupId, userId, request)
                    .stream()
                    .map(TransactionMapper.INSTANCE::entityToDto)
                    .toList();
        }

        if (startDate != null && endDate != null) {
            return repository.findAllByDateBetweenAndUserIdOrderByDateDesc(startDate.atStartOfDay(),
                            endDate.atStartOfDay(),
                            userId,
                            request)
                    .stream()
                    .map(TransactionMapper.INSTANCE::entityToDto)
                    .toList();
        }

        return repository.findAllByUserIdOrderByDateDesc(userId, request)
                .stream()
                .map(TransactionMapper.INSTANCE::entityToDto)
                .toList();
    }

    @Override
    public TransactionDto saveNewTransaction(final TransactionDto newDto) {
        final Transaction transaction = TransactionMapper.INSTANCE.dtoToEntity(newDto);
        final Transaction save = repository.save(transaction);

        return TransactionMapper.INSTANCE.entityToDto(save);
    }

    private Pageable getPageable(Integer pageSize, Integer pageNumber) {
        final Pageable request;
        if (pageSize != null && pageNumber != null) {
            request = PageRequest.of(
                    pageNumber,
                    pageSize,
                    Sort.by("date").descending()
            );
        } else {
            request = PageRequest.of(
                    properties.getDefaultPageNumber(),
                    properties.getDefaultPageSize(),
                    Sort.by("date").descending()
            );
        }
        return request;
    }

}
