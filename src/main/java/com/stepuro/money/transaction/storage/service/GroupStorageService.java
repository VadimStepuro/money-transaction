package com.stepuro.money.transaction.storage.service;

import com.stepuro.money.transaction.web.dto.GroupDto;

import java.util.List;

public interface GroupStorageService {
    List<GroupDto> findAll();
}
