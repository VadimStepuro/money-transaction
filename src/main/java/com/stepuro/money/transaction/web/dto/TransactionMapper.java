package com.stepuro.money.transaction.web.dto;

import com.stepuro.money.transaction.storage.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    Transaction dtoToEntity(TransactionDto transactionDto);

    TransactionDto entityToDto(Transaction transaction);
}
