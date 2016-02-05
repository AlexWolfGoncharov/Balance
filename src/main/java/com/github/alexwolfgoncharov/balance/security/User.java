package com.github.alexwolfgoncharov.balance.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Set;

/**
 * Created by alexwolf on 01.02.16.
 */
@Entity

@Table(name = "users", schema = "Balance")
public class User implements UserDetails {
    @Id
    @Column(name = "ID", nullable = false)
    private int id;
    @Basic
    @Column(name = "login", nullable = false, length = 50)

    private String login;
    @Basic
    @Column(name = "password", nullable = false, length = 150)

    private String password;
    @Basic
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;
    @Basic
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;
    @Basic
    @Column(name = "middle_name", nullable = true, length = 50)
    private String middleName;
    @Basic
    @Column(name = "access", nullable = false)
    private boolean access;
    @Basic
    @Column(name = "create_time", nullable = false)
    private Timestamp createTime;
    @Basic
    @Column(name = "last_change_time", nullable = false)
    private Timestamp lastChangeTime;
    @Basic
    @Column(name = "email", nullable = true, length = 50)
    private String email;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "app_users_roles", joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "ID") }, inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "ID") })

    private Set<UserRoles> userRoles;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }


    public boolean isAccess() {
        return access;
    }

    public void setAccess(boolean access) {
        this.access = access;
    }


    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }


    public Timestamp getLastChangeTime() {
        return lastChangeTime;
    }

    public void setLastChangeTime(Timestamp lastChangeTime) {
        this.lastChangeTime = lastChangeTime;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User users = (User) o;

        if (id != users.id) return false;
        if (access != users.access) return false;
        if (login != null ? !login.equals(users.login) : users.login != null) return false;
        if (lastName != null ? !lastName.equals(users.lastName) : users.lastName != null) return false;
        if (firstName != null ? !firstName.equals(users.firstName) : users.firstName != null) return false;
        if (middleName != null ? !middleName.equals(users.middleName) : users.middleName != null) return false;
        if (createTime != null ? !createTime.equals(users.createTime) : users.createTime != null) return false;
        if (lastChangeTime != null ? !lastChangeTime.equals(users.lastChangeTime) : users.lastChangeTime != null)
            return false;
        if (email != null ? !email.equals(users.email) : users.email != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (access ? 1 : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (lastChangeTime != null ? lastChangeTime.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }



    public void setUserRoles(Set<UserRoles> userRoles) {
        this.userRoles = userRoles;
    }



    public Set<UserRoles> getUserRoles() {
        return userRoles;
    }

    @Override


//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "app_users_roles",
//            joinColumns = { @JoinColumn(name = "user_id") },
//            inverseJoinColumns = { @JoinColumn(name = "role_id") })


    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userRoles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return access;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return access;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", access=" + access +
                ", createTime=" + createTime +
                ", lastChangeTime=" + lastChangeTime +
                ", email='" + email + '\'' +
//                ", userRoles=" + userRoles.size() +
                '}';
    }
}
