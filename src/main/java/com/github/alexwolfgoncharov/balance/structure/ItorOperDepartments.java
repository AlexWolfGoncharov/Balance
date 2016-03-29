package com.github.alexwolfgoncharov.balance.structure;

import java.math.BigDecimal;

/**
 * Created by alexwolf on 13.02.16.
 */
public class ItorOperDepartments {
    private Departments departments;
    private Contracts contracts;
    private BigDecimal summa;
    private BigDecimal ndc;
    private int countOfOperations;

    public Departments getDepartments() {
        return departments;
    }

    public void setDepartments(Departments departments) {
        this.departments = departments;
    }

    public Contracts getContracts() {
        return contracts;
    }

    public void setContracts(Contracts contracts) {
        this.contracts = contracts;
    }

    public BigDecimal getSumma() {
        return summa;
    }

    public void setSumma(BigDecimal summa) {
        this.summa = summa;
    }

    public BigDecimal getNdc() {
        return ndc;
    }

    public void setNdc(BigDecimal ndc) {
        this.ndc = ndc;
    }

    public int getCountOfOperations() {
        return countOfOperations;
    }

    public void setCountOfOperations(int countOfOperations) {
        this.countOfOperations = countOfOperations;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItorOperDepartments)) return false;

        ItorOperDepartments that = (ItorOperDepartments) o;

        if (getCountOfOperations() != that.getCountOfOperations()) return false;
        if (getDepartments() != null ? !getDepartments().equals(that.getDepartments()) : that.getDepartments() != null)
            return false;
        if (getContracts() != null ? !getContracts().equals(that.getContracts()) : that.getContracts() != null)
            return false;
        if (!getSumma().equals(that.getSumma())) return false;
        return getNdc() != null ? getNdc().equals(that.getNdc()) : that.getNdc() == null;

    }

    @Override
    public int hashCode() {
        int result = getDepartments() != null ? getDepartments().hashCode() : 0;
        result = 31 * result + (getContracts() != null ? getContracts().hashCode() : 0);
        result = 31 * result + getSumma().hashCode();
        result = 31 * result + (getNdc() != null ? getNdc().hashCode() : 0);
        result = 31 * result + getCountOfOperations();
        return result;
    }

    @Override
    public String toString() {
        return "ItorOperDepartments{" +
                "departments=" + departments +
                ", contracts=" + contracts +
                ", summa=" + summa +
                ", ndcl=" + ndc +
                ", countOfOperations=" + countOfOperations +
                '}';
    }


}
