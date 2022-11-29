package com.doul.sy.secucom1;

import com.doul.sy.secucom1.configuration.RsakeysConfig;
import com.doul.sy.secucom1.model.Collaborateur;
import com.doul.sy.secucom1.model.Role;
import com.doul.sy.secucom1.service.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
@EnableConfigurationProperties(RsakeysConfig.class)
public class Secucom1Application {

	public static void main(String[] args) {

		SpringApplication.run(Secucom1Application.class, args);
	}

@Bean
public PasswordEncoder passwordEncoder(){
	return new BCryptPasswordEncoder();
 }


@Bean
CommandLineRunner start (AccountService accountService){

		return args -> {
			accountService.addNewRole(new Role(1L, "USER"));
			accountService.addNewRole(new Role(2L, "ADMIN"));

			accountService.addNewCollaborateur(new Collaborateur(1L, "Collaborateur1", "1234",new ArrayList<>()));
			accountService.addNewCollaborateur(new Collaborateur(2L, "Collaborateur2", "1234",new ArrayList<>()));
			accountService.addNewCollaborateur(new Collaborateur(3L, "Collaborateur3", "1234",new ArrayList<>()));
			accountService.addNewCollaborateur(new Collaborateur(4L, "Collaborateur4", "1234",new ArrayList<>()));

			accountService.addRoleToCollaborateur("Collaborateur1", "ADMIN");
			accountService.addRoleToCollaborateur("Collaborateur2", "USER");
			accountService.addRoleToCollaborateur("Collaborateur3", "USER");
			accountService.addRoleToCollaborateur("Collaborateur4", "USER");
			accountService.addRoleToCollaborateur("Collaborateur4", "ADMIN");


		};
}
}