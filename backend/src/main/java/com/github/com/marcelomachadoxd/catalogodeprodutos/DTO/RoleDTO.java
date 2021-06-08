package com.github.com.marcelomachadoxd.catalogodeprodutos.DTO;

import com.github.com.marcelomachadoxd.catalogodeprodutos.model.entities.Role;

import java.io.Serializable;

public class RoleDTO implements Serializable {

    private Long Id;
    private String authority;

    public RoleDTO() {
    }

    public RoleDTO(Long id, String authority) {
        Id = id;
        this.authority = authority;
    }

    public RoleDTO(Role entity) {
        Id = entity.getId();
        this.authority = entity.getAuthority();
    }


    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
