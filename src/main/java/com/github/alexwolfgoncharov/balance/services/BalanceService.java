package com.github.alexwolfgoncharov.balance.services;

import com.github.alexwolfgoncharov.balance.structure.Balance;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alexwolf on 02.02.16.
 */
@Service
public interface BalanceService {

    void add(Balance contract);
    Balance getById(int ID, Object o);
    List<Balance> getAll(Object o);
    void modify (Balance contract);
    void delete(Balance contract);
}
