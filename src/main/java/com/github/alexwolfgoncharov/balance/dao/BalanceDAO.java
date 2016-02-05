package com.github.alexwolfgoncharov.balance.dao;

import com.github.alexwolfgoncharov.balance.structure.Balance;

import java.util.List;

/**
 * Created by alexwolf on 31.01.16.
 */
public interface BalanceDAO {
    void add(Balance contract);
    Balance getById(int ID, Object o);
    List<Balance> getAll(Object o);
    void modify (Balance contract);
    void delete(Balance contract);
}
