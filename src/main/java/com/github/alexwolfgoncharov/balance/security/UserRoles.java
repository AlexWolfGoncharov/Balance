package com.github.alexwolfgoncharov.balance.security;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 * Created by alexwolf on 01.02.16.
 */

@Entity
@Table(name = "user_roles", schema = "Balance")
public class UserRoles implements GrantedAuthority {
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "role", nullable = false, length = 15)
    private String role;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserRoles userRoles = (UserRoles) o;

        if (id != userRoles.id) return false;
        if (role != null ? !role.equals(userRoles.role) : userRoles.role != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (role != null ? role.hashCode() : 0);
        return result;
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + role;
    }

    @Override
    public String toString() {
        return "UserRoles{" +
                "id=" + id +
                ", role='" + role + '\'' +
                '}';
    }
}
