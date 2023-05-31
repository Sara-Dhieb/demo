package com.myproject.BoardManagement.demo.repository;

import com.myproject.BoardManagement.demo.model.Action;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ActionRepository extends JpaRepository<Action,Integer> {
    List<Action> findByActionType(Action.ActionType actionType);
    List<Action> findByDate(Date date);
    List<Action>  findByDateBetween(Date start, Date end);
}
