package com.stepuro.money.transaction.web.dto;

import com.stepuro.money.transaction.storage.entity.Group;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GroupMapper {
    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

    Group dtoToEntity(GroupDto groupDto);

    GroupDto entityToDto(Group group);
}
