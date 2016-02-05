package com.github.alexwolfgoncharov.balance.structure;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Created by alexwolf on 31.01.16.
 */
@Entity
@Table(name = "contr_agents", schema = "Balance")
public class ContrAgents extends Balance {
    private int id;
    private String name;
    private String address;
    @JsonIgnore
    List<Contracts> allContracts;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 50)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 100)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "contr_agent_id")
    @org.hibernate.annotations.OrderBy(clause = "ID")
    public List<Contracts> getAllContracts() {
        return allContracts;
    }

    public void setAllContracts(List<Contracts> allContracts) {
        this.allContracts = allContracts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContrAgents that = (ContrAgents) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ContrAgents{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", allContractsCount=" + allContracts.size() +
                '}';
    }
}
