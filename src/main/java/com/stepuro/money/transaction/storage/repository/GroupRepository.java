package com.stepuro.money.transaction.storage.repository;

import com.stepuro.money.transaction.storage.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GroupRepository extends JpaRepository<Group, UUID> {
}
