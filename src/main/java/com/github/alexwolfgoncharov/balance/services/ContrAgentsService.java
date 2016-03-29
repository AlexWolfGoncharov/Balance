package com.github.alexwolfgoncharov.balance.services;

import com.github.alexwolfgoncharov.balance.structure.ContrAgents;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alexwolf on 01.02.16.
 */
@Service
public interface ContrAgentsService {
    void add(ContrAgents contract);
    ContrAgents getById(int ID);
    List<ContrAgents> getAll();
    void modify (ContrAgents contract);
    void delete(ContrAgents contract);
}
