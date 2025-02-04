package com.ignis.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.ignis.api.dto.AppRoleDTO;
import com.ignis.api.model.AppRole;

@Mapper
public interface AppRoleMapper {
    AppRoleMapper INSTANCE = Mappers.getMapper(AppRoleMapper.class);

    @Mapping(source = "id", target = "roleId")
    AppRoleDTO toDTO(AppRole entity);

    @Mapping(source = "roleId", target = "id")
    AppRole toEntity(AppRoleDTO dto);
}