package com.doul.sy.secucom1.service;

import com.doul.sy.secucom1.model.Collaborateur;
import com.doul.sy.secucom1.model.Role;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface AccountService {
    Collaborateur addNewCollaborateur(Collaborateur collaborateur);
    Role addNewRole(Role role);

    void addRoleToCollaborateur(String username, String roleName);
    Collaborateur loadUserByUsername(String username);

    List<Collaborateur> listCollaborateur();

}
