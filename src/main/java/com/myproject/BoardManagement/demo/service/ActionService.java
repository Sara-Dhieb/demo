package com.myproject.BoardManagement.demo.service;

import com.myproject.BoardManagement.demo.model.Action;

import java.util.Date;
import java.util.List;

public interface ActionService {
    List<Action> getActionsByDateRange(Date start, Date end);
    List<Action> getActionsByActionType(Action.ActionType actionType);
    List<Action> getActionsByDate(Date date);
}
