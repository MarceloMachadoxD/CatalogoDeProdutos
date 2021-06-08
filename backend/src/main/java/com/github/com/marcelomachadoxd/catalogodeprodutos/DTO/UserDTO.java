package com.github.com.marcelomachadoxd.catalogodeprodutos.DTO;

import com.github.com.marcelomachadoxd.catalogodeprodutos.model.entities.Role;
import com.github.com.marcelomachadoxd.catalogodeprodutos.model.entities.User;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserDTO implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    private Set<RoleDTO> role = new HashSet<>();


    public UserDTO() {
    }

    public UserDTO(Long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public UserDTO(User entity, Set<Role> roles) {
        this.id = entity.getId();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
        this.email = entity.getEmail();

        roles.forEach(role -> this.role.add(new RoleDTO(role)));

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<RoleDTO> getRole() {
        return role;
    }

}
