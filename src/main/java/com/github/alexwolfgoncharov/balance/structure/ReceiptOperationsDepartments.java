package com.github.alexwolfgoncharov.balance.structure;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * Created by alexwolf on 03.02.16.
 */
@Entity
@Table(name = "receipt_operations_departments", schema = "Balance_demo")
public class ReceiptOperationsDepartments {
    private long id;
    private Timestamp time;
    private Departments departmentId;
    private BigDecimal summa;
    private BigDecimal ndc;
    private String description;
    private ReceiptOperationsContracts receptOpContrId;

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
    public BigDecimal getSumma() {
        return summa;
    }

    public void setSumma(BigDecimal summa) {
        this.summa = summa;
    }

    @Basic
    @Column(name = "NDC", nullable = true, precision = 0)
    public BigDecimal getNdc() {
        return ndc;
    }

    public void setNdc(BigDecimal ndc) {
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


    @ManyToOne
    @JoinColumn(name = "recept_op_contr_id")
    @Fetch(FetchMode.SELECT)

    public ReceiptOperationsContracts getReceptOpContrId() {
        return receptOpContrId;
    }

    public void setReceptOpContrId(ReceiptOperationsContracts receptOpContrId) {
        this.receptOpContrId = receptOpContrId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ReceiptOperationsDepartments)) return false;

        ReceiptOperationsDepartments that = (ReceiptOperationsDepartments) o;

        if (getId() != that.getId()) return false;
        if (!getTime().equals(that.getTime())) return false;
        if (!getDepartmentId().equals(that.getDepartmentId())) return false;
        if (!getSumma().equals(that.getSumma())) return false;
        if (getNdc() != null ? !getNdc().equals(that.getNdc()) : that.getNdc() != null) return false;
        if (getDescription() != null ? !getDescription().equals(that.getDescription()) : that.getDescription() != null)
            return false;
        return getReceptOpContrId().equals(that.getReceptOpContrId());

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getTime().hashCode();
        result = 31 * result + getDepartmentId().hashCode();
        result = 31 * result + getSumma().hashCode();
        result = 31 * result + (getNdc() != null ? getNdc().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + getReceptOpContrId().hashCode();
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
                ", receptOpContrId=" + receptOpContrId.getId() +
                '}';
    }

}
