package com.doul.sy.secucom1.controller;

import com.doul.sy.secucom1.model.Collaborateur;
import com.doul.sy.secucom1.model.Role;
import com.doul.sy.secucom1.service.AccountService;
import lombok.Data;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
public class AppTest {

    private AccountService accountService;

    public  AppTest(AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping("/listcollab")
    @PreAuthorize("hasAuthority('SCOPE_USER')")
    public List<Collaborateur> collaborateurList() {
        return accountService.listCollaborateur();
    }

    @PostMapping("/saveCollab")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public Collaborateur saveCollab(@RequestBody Collaborateur collaborateur) {
        return accountService.addNewCollaborateur(collaborateur);

    }
    @PostMapping("/roles")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public Role saveRole(@RequestBody Role role) {
        return accountService.addNewRole(role);

    }
    @PostMapping("/addRoleToCollab")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    void addRoleToCollaborateur(@RequestBody RoleCollabForm roleCollabForm) {
        accountService.addRoleToCollaborateur(roleCollabForm.getUsername(), roleCollabForm.getRoleName());

    }
}

@Data
class RoleCollabForm{
    private String username;
    private String roleName;


}