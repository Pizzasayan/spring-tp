package com.farda.initializer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.farda.model.ERole;
import com.farda.model.Role;
import com.farda.repository.RoleRepository;

import jakarta.annotation.PostConstruct;
import java.util.Arrays;

@Component
public class RoleInitializer {

    @Autowired
    private RoleRepository roleRepository;

    @PostConstruct
    public void init() {
        // Parcourir tous les rôles définis dans l'enum ERole
        Arrays.stream(ERole.values()).forEach(this::createRoleIfNotFound);
    }

    private void createRoleIfNotFound(ERole roleEnum) {
        // Vérifier si le rôle existe déjà dans la base
        if (!roleRepository.findByName(roleEnum).isPresent()) {
            // Créer et enregistrer le rôle si inexistant
            Role role = new Role();
            role.setName(roleEnum);
            roleRepository.save(role);
            System.out.println("Rôle ajouté : " + roleEnum);
        } else {
            System.out.println("Le rôle " + roleEnum + " existe déjà.");
        }
    }
}