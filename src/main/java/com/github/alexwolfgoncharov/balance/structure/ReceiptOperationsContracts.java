package com.github.alexwolfgoncharov.balance.structure;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.math.BigDecimal;
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
    private BigDecimal summa;
    private BigDecimal ndc;
    private String description;
    private List<ReceiptOperationsDepartments> receiptOperationsDepartmentList;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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


    @OneToMany(cascade = CascadeType.ALL,  fetch = FetchType.EAGER)
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
        if (!(o instanceof ReceiptOperationsContracts)) return false;

        ReceiptOperationsContracts that = (ReceiptOperationsContracts) o;

        if (getId() != that.getId()) return false;
        if (!getTime().equals(that.getTime())) return false;
        if (!getContractId().equals(that.getContractId())) return false;
        if (!getSumma().equals(that.getSumma())) return false;
        if (getNdc() != null ? !getNdc().equals(that.getNdc()) : that.getNdc() != null) return false;
        if (getDescription() != null ? !getDescription().equals(that.getDescription()) : that.getDescription() != null)
            return false;
        return getReceiptOperationsDepartmentList() != null ? getReceiptOperationsDepartmentList().equals(that.getReceiptOperationsDepartmentList()) : that.getReceiptOperationsDepartmentList() == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getTime().hashCode();
        result = 31 * result + getContractId().hashCode();
        result = 31 * result + getSumma().hashCode();
        result = 31 * result + (getNdc() != null ? getNdc().hashCode() : 0);
        result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
        result = 31 * result + (getReceiptOperationsDepartmentList() != null ? getReceiptOperationsDepartmentList().hashCode() : 0);
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
