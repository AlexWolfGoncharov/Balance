package com.github.alexwolfgoncharov.balance.structure;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Created by alexwolf on 31.01.16.
 */
@Entity
@Table(name = "contracts", schema = "Balance")
public class Contracts extends Balance{
    private int id;
    private ContrAgents contrAgentId;
    private String contractNumber;
    private Date startDate;
    private double summ;
    private String description;
    @JsonIgnore
    private List <ReceiptOperationsContracts> operationsContractses;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @OneToOne
    @JoinColumn(name = "contr_agent_id")
    public ContrAgents getContrAgentId() {
        return contrAgentId;
    }

    public void setContrAgentId(ContrAgents contrAgentId) {
        this.contrAgentId = contrAgentId;
    }

    @Basic
    @Column(name = "contract_number", nullable = false, length = 20)
    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    @Basic
    @Column(name = "start_date", nullable = true)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "summ", nullable = false, precision = 0)
    public double getSumm() {
        return summ;
    }

    public void setSumm(double summ) {
        this.summ = summ;
    }

    @Basic
    @Column(name = "description", nullable = true, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JoinColumn(name = "contract_id")
    @org.hibernate.annotations.OrderBy(clause = "ID")
    public List<ReceiptOperationsContracts> getOperationsContractses() {
        return operationsContractses;
    }

    public void setOperationsContractses(List<ReceiptOperationsContracts> operationsContractses) {
        this.operationsContractses = operationsContractses;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contracts that = (Contracts) o;

        if (id != that.id) return false;
        if (Double.compare(that.summ, summ) != 0) return false;
        if (contractNumber != null ? !contractNumber.equals(that.contractNumber) : that.contractNumber != null)
            return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (contractNumber != null ? contractNumber.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        temp = Double.doubleToLongBits(summ);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Contracts{" +
                "id=" + id +
                ", contrAgentId={name: " + contrAgentId.getName() + ", id="+contrAgentId.getId() + "}"+
                ", contractNumber='" + contractNumber + '\'' +
                ", startDate=" + startDate +
                ", summ=" + summ +
                ", description='" + description + '\'' +
                '}';
    }
}
