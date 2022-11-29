package com.doul.sy.secucom1.serviceImpl;

import com.doul.sy.secucom1.model.Collaborateur;
import com.doul.sy.secucom1.repository.CollaborateurRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;

@Service
@Slf4j
public class Custumer implements UserDetailsService {

    private CollaborateurRepository collaborateurRepository;

    public Custumer(CollaborateurRepository collaborateurRepository) {
        this.collaborateurRepository = collaborateurRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if(username.trim().isEmpty()){
            throw new UsernameNotFoundException("le collaborateur n'existe pas");
        }
        Collaborateur collaborateur = collaborateurRepository.findByUsername(username);
        if(username == null){
            log.error("Collaborateur non trouvé ");
            throw new UsernameNotFoundException("Collaborateur non trouvé");
        }else {
            log.info("Collaborateur trouvé", username);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        collaborateur.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
            return new org.springframework.security.core.userdetails.User(collaborateur.getUsername(), collaborateur.getPassword(), authorities);
    }

}
