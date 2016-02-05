package com.github.alexwolfgoncharov.balance.dao;

import com.github.alexwolfgoncharov.balance.structure.Contracts;

import java.util.List;

/**
 * Created by alexwolf on 31.01.16.
 */
public interface  ContractsDAO {
    void add(Contracts contract);
    Contracts getById(int ID);
    List<Contracts> getAll();
    void modify (Contracts contract);
    void delete(Contracts contract);



}
