package com.github.alexwolfgoncharov.balance.services;

import com.github.alexwolfgoncharov.balance.structure.ContrAgents;

import java.util.List;

/**
 * Created by alexwolf on 01.02.16.
 */
public interface ContrAgentsService {
    void add(ContrAgents contract);
    ContrAgents getById(int ID);
    List<ContrAgents> getAll();
    void modify (ContrAgents contract);
    void delete(ContrAgents contract);
}
