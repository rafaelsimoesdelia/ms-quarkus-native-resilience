package com.ignis.api.repository;

import org.springframework.data.repository.CrudRepository;

import com.ignis.api.model.AppRole;

public interface AppRoleRepository extends CrudRepository<AppRole, Long> {
}