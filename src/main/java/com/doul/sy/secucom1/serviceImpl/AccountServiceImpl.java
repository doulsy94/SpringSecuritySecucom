package com.doul.sy.secucom1.serviceImpl;

import com.doul.sy.secucom1.model.Collaborateur;
import com.doul.sy.secucom1.model.Role;
import com.doul.sy.secucom1.repository.CollaborateurRepository;
import com.doul.sy.secucom1.repository.RoleRepository;
import com.doul.sy.secucom1.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class AccountServiceImpl implements AccountService {

    private PasswordEncoder passwordEncoder;
    private CollaborateurRepository collaborateurRepository;
    private RoleRepository roleRepository;

    public AccountServiceImpl(PasswordEncoder passwordEncoder, CollaborateurRepository collaborateurRepository, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.collaborateurRepository = collaborateurRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public Collaborateur addNewCollaborateur(Collaborateur collaborateur) {
        log.info("Ajout collaborateur", collaborateur.getUsername());
        collaborateur.setPassword(passwordEncoder.encode(collaborateur.getPassword()));

        return collaborateurRepository.save(collaborateur);
    }

    @Override
    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToCollaborateur(String username, String roleName) {
        Collaborateur collaborateur = collaborateurRepository.findByUsername(username);
        Role role = roleRepository.findByRoleName(roleName);
        collaborateur.getRoles().add(role);

    }
    @Override
    public Collaborateur loadUserByUsername(String username) {
        return collaborateurRepository.findByUsername(username);
    }

    @Override
    public List<Collaborateur> listCollaborateur() {
        return collaborateurRepository.findAll();
    }


}
