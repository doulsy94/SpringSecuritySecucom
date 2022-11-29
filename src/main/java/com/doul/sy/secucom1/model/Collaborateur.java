package com.doul.sy.secucom1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Collaborateur {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();
}
