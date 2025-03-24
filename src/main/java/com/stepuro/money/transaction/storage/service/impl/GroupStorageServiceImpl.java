package com.stepuro.money.transaction.storage.service.impl;

import com.stepuro.money.transaction.storage.repository.GroupRepository;
import com.stepuro.money.transaction.storage.service.GroupStorageService;
import com.stepuro.money.transaction.web.dto.GroupDto;
import com.stepuro.money.transaction.web.dto.GroupMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupStorageServiceImpl implements GroupStorageService {
    private final GroupRepository repository;

    @Override
    public List<GroupDto> findAll() {
        return repository.findAll()
                .stream()
                .map(GroupMapper.INSTANCE::entityToDto)
                .toList();
    }
}
