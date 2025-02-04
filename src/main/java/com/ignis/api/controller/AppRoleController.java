package com.ignis.api.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import com.ignis.api.dto.AppRoleDTO;
import com.ignis.api.interceptor.ResilienceInterceptor;
import com.ignis.api.mapper.AppRoleMapper;
import com.ignis.api.model.AppRole;
import com.ignis.api.service.AppRoleService;

import jakarta.inject.Inject;
import jakarta.interceptor.Interceptors;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

/**
 * 
 * 
 * @author rsdelia
 */
@Path("/roles")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Interceptors(ResilienceInterceptor.class) // Aplica a resiliência automaticamente
public class AppRoleController {

    @Inject
    AppRoleService service;

    @GET
    public List<AppRoleDTO> getAllRoles() {
	return service.findAll().stream().map(AppRoleMapper.INSTANCE::toDTO).collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    public Response getRoleById(@PathParam("id") Long id) {
	return service.findById(id).map(role -> Response.ok(AppRoleMapper.INSTANCE.toDTO(role)).build())
		.orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @POST
    public Response createRole(AppRoleDTO dto) {
	AppRole newRole = service.create(AppRoleMapper.INSTANCE.toEntity(dto));
	URI location = UriBuilder.fromResource(AppRoleController.class).path("/{id}").build(newRole.getId());

	return Response.created(location).entity(AppRoleMapper.INSTANCE.toDTO(newRole)).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateRole(@PathParam("id") Long id, AppRoleDTO dto) {
	boolean updated = service.update(id, toEntity(dto));
	return updated ? Response.ok().build() : Response.status(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteRole(@PathParam("id") Long id) {
	service.delete(id);
	return Response.noContent().build();
    }

    private AppRole toEntity(AppRoleDTO dto) {
	return new AppRole(dto.getRoleId(), dto.getName());
    }
}