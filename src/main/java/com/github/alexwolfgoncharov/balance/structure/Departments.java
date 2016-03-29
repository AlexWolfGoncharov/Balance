package com.github.alexwolfgoncharov.balance.structure;

import javax.persistence.*;

/**
 * Created by alexwolf on 31.01.16.
 */
@Entity
@Table(name = "departments", schema = "Balance_demo")
public class Departments extends Balance{
    private int id;
    private String nameOfDepartment;
    private String description;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name_of_department", nullable = false, length = 30)
    public String getNameOfDepartment() {
        return nameOfDepartment;
    }

    public void setNameOfDepartment(String nameOfDepartment) {
        this.nameOfDepartment = nameOfDepartment;
    }

    @Basic
    @Column(name = "description", nullable = true, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Departments that = (Departments) o;

        if (id != that.id) return false;
        if (nameOfDepartment != null ? !nameOfDepartment.equals(that.nameOfDepartment) : that.nameOfDepartment != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (nameOfDepartment != null ? nameOfDepartment.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Departments{" +
                "id=" + id +
                ", nameOfDepartment='" + nameOfDepartment + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
