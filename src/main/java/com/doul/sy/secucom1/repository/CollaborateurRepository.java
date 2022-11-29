package com.doul.sy.secucom1.repository;

import com.doul.sy.secucom1.model.Collaborateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollaborateurRepository extends JpaRepository<Collaborateur, Long> {
    Collaborateur findByUsername(String username);
}
