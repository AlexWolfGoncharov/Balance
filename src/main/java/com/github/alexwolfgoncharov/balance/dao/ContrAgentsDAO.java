package com.github.alexwolfgoncharov.balance.dao;

import com.github.alexwolfgoncharov.balance.structure.ContrAgents;

import java.util.List;

/**
 * Created by alexwolf on 31.01.16.
 */
public interface ContrAgentsDAO {

    void add(ContrAgents contract);
    ContrAgents getById(int ID);
    List<ContrAgents> getAll();
    void modify (ContrAgents contract);
    void delete(ContrAgents contract);
}
