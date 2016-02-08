package com.github.alexwolfgoncharov.balance.structure;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.List;

/**
 * Created by alexwolf on 31.01.16.
 */
@Entity
@Table(name = "receipt_operations_contracts", schema = "Balance")
public class ReceiptOperationsContracts {
    private long id;
    private Timestamp time;
    private Contracts contractId;
    private double summa;
    private Double ndc;
    private String description;
    private List<ReceiptOperationsDepartments> receiptOperationsDepartmentList;

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
    @JoinColumn(name="contract_id")
    public Contracts getContractId() {
        return contractId;
    }

    public void setContractId(Contracts contractId) {
        this.contractId = contractId;
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
    public Double getNdc() {
        return ndc;
    }

    public void setNdc(Double ndc) {
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


    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    @JoinColumn(name = "recept_op_contr_id")
    @org.hibernate.annotations.OrderBy(clause = "ID")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
            org.hibernate.annotations.CascadeType.DELETE,
            org.hibernate.annotations.CascadeType.MERGE,
            org.hibernate.annotations.CascadeType.PERSIST,
            org.hibernate.annotations.CascadeType.DELETE_ORPHAN,
            org.hibernate.annotations.CascadeType.REMOVE,
            org.hibernate.annotations.CascadeType.DETACH
    })
    public List<ReceiptOperationsDepartments> getReceiptOperationsDepartmentList() {
        return receiptOperationsDepartmentList;
    }

    public void setReceiptOperationsDepartmentList(List<ReceiptOperationsDepartments> receiptOperationsDepartmentList) {
        this.receiptOperationsDepartmentList = receiptOperationsDepartmentList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReceiptOperationsContracts that = (ReceiptOperationsContracts) o;

        if (id != that.id) return false;
        if (contractId != that.contractId) return false;
        if (Double.compare(that.summa, summa) != 0) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        if (ndc != null ? !ndc.equals(that.ndc) : that.ndc != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + contractId.getId();
        temp = Double.doubleToLongBits(summa);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (ndc != null ? ndc.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ReceiptOperationsContracts{" +
                "id=" + id +
                ", time=" + time.toLocaleString() +
                ", contractId=" + contractId.getId() +
                ", summa=" + summa +
                ", ndc=" + ndc +
                ", description='" + description + '\'' +
                ", receiptOperationsDepartmentList=" + receiptOperationsDepartmentListToString() +
                '}';
    }


    public String receiptOperationsDepartmentListToString(){

        String rez = "0";
        if (this.receiptOperationsDepartmentList != null) {
            rez = "[";
            int coun = 1;
            for (ReceiptOperationsDepartments dep : this.receiptOperationsDepartmentList) {
                rez += "{";
                rez += dep.toString();
                rez += "}";
                if (coun != this.receiptOperationsDepartmentList.size())
                    rez += ",";

                coun++;

            }
            rez += "]";
        }

        return rez;
    }
}
