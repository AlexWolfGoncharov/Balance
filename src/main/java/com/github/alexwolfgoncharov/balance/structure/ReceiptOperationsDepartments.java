package com.github.alexwolfgoncharov.balance.structure;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by alexwolf on 03.02.16.
 */
@Entity
@Table(name = "receipt_operations_departments", schema = "Balance")
public class ReceiptOperationsDepartments {
    private long id;
    private Timestamp time;
    private Departments departmentId;
    private double summa;
    private double ndc;
    private String description;
    private long receptOpContrId;

    @Id
    @Column(name = "ID", nullable = false)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "time", nullable = false)
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

//    @Basic
//    @Column(name = "department_id", nullable = false)
    @ManyToOne
    @JoinColumn(name = "department_id")
    public Departments getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Departments departmentId) {
        this.departmentId = departmentId;
    }

    @Basic
    @Column(name = "summa", nullable = false, precision = 0)
    public double getSumma() {
        return summa;
    }

    public void setSumma(double summa) {
        this.summa = summa;
    }

    @Basic
    @Column(name = "NDC", nullable = true, precision = 0)
    public double getNdc() {
        return ndc;
    }

    public void setNdc(double ndc) {
        this.ndc = ndc;
    }

    @Basic
    @Column(name = "description", nullable = true, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "recept_op_contr_id")
    public long getReceptOpContrId() {
        return receptOpContrId;
    }

    public void setReceptOpContrId(long receptOpContrId) {
        this.receptOpContrId = receptOpContrId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReceiptOperationsDepartments that = (ReceiptOperationsDepartments) o;

        if (id != that.id) return false;
        if (departmentId != that.departmentId) return false;
        if (Double.compare(that.summa, summa) != 0) return false;
        if (Double.compare(that.ndc, ndc) != 0) return false;
        if (receptOpContrId != that.receptOpContrId) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + departmentId.getId();
        temp = Double.doubleToLongBits(summa);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(ndc);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (int) (receptOpContrId ^ (receptOpContrId >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "ReceiptOperationsDepartments{" +
                "id=" + id +
                ", time=" + time +
                ", departmentId=" + departmentId.getId() +
                ", summa=" + summa +
                ", ndc=" + ndc +
                ", description='" + description + '\'' +
                ", receptOpContrId=" + receptOpContrId +
                '}';
    }

}
