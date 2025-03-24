package com.stepuro.money.transaction.web;

import com.stepuro.money.transaction.storage.service.GroupStorageService;
import com.stepuro.money.transaction.web.dto.GroupDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(
        value = "/api/v1/groups",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
)
@RequiredArgsConstructor
public class GroupController {
    private final GroupStorageService storageService;

    @GetMapping
    public List<GroupDto> groupDtoList() {
        return storageService.findAll();
    }
}
