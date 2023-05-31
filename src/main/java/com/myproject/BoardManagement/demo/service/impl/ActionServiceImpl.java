package com.myproject.BoardManagement.demo.service.impl;

import com.myproject.BoardManagement.demo.model.Action;
import com.myproject.BoardManagement.demo.repository.ActionRepository;
import com.myproject.BoardManagement.demo.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ActionServiceImpl implements ActionService {
    @Autowired
    ActionRepository actionRepository;
    @Override
    public List<Action> getActionsByDateRange(Date start, Date end) {

            return actionRepository.findByDateBetween(start, end);
        }
    @Override
    public List<Action> getActionsByDate(Date date) {

        return actionRepository.findByDate(date);
    }

        @Override
    public List<Action> getActionsByActionType(Action.ActionType actionType) {
        return actionRepository.findByActionType(actionType);
    }


}
