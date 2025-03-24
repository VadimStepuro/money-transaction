package com.stepuro.money.transaction.web.dto;

import com.stepuro.money.transaction.storage.entity.SalesPoint;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SalesPointMapper {
    SalesPointMapper INSTANCE = Mappers.getMapper(SalesPointMapper.class);

    SalesPoint dtoToEntity(SalesPointDto salesPointDto);

    SalesPointDto entityToDto(SalesPoint salesPoint);
}
