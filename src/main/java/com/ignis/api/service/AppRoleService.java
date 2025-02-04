package com.ignis.api.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.jboss.logging.Logger;

import com.ignis.api.interceptor.Resilience;
import com.ignis.api.model.AppRole;
import com.ignis.api.repository.AppRoleRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@Resilience // Aplica resiliência em todos os métodos automaticamente
@ApplicationScoped
public class AppRoleService {

    @Inject
    AppRoleRepository repository;

    private static final Logger LOG = Logger.getLogger(AppRoleService.class);

    public List<AppRole> findAll() {
	LOG.info("Buscando todas as roles...");
	return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public Optional<AppRole> findById(Long id) {
	LOG.infof("Buscando role com ID: %d", id);
	return repository.findById(id);
    }

    @Resilience
    @Transactional
    public AppRole create(AppRole role) {
	LOG.infof("Criando nova role: %s", role.getName());

	// Simula um delay de 5 segundos para ativar o Timeout
	try {
	    Thread.sleep(5000);
	} catch (InterruptedException e) {
	    Thread.currentThread().interrupt();
	}

	repository.save(role);
	return role;
    }

    @Transactional
    public boolean update(Long id, AppRole updatedRole) {
	return repository.findById(id).map(existingRole -> {
	    LOG.infof("Atualizando role ID %d para %s", id, updatedRole.getName());
	    existingRole.setName(updatedRole.getName());
	    return true;
	}).orElse(false);
    }

    @Transactional
    public void delete(Long id) {
	LOG.infof("Deletando role com ID %d", id);
	repository.deleteById(id);
    }
}