package com.github.alexwolfgoncharov.balance.services;

import com.github.alexwolfgoncharov.balance.structure.Contracts;

import java.util.List;

/**
 * Created by alexwolf on 01.02.16.
 */
public interface ContractsService {
    void add(Contracts contract);
    Contracts getById(int ID);
    List<Contracts> getAll();
    void modify (Contracts contract);
    void delete(Contracts contract);
}
