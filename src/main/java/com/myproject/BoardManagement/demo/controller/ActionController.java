package com.myproject.BoardManagement.demo.controller;

import com.myproject.BoardManagement.demo.model.Action;
import com.myproject.BoardManagement.demo.model.Reunion;
import com.myproject.BoardManagement.demo.service.impl.ActionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@CrossOrigin("http://localhost:4200/")
@RestController
public class ActionController {
    @Autowired
    ActionServiceImpl actionServiceImpl;
    @GetMapping("date/actions/{fromDate}/{toDate}")
//    update to request param (path variabale)
    public ResponseEntity<List<Action>> getActionsByDateRange(
            @PathVariable("fromDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fromDate,
            @PathVariable("toDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date toDate) {
        List<Action> actions = actionServiceImpl.getActionsByDateRange(fromDate, toDate);
      return  ResponseEntity.ok(actions);
    }
    @GetMapping("date/actions/{date}")
//    update to request param (path variabale)
    public ResponseEntity<List<Action>> getActionsByDateRange(
            @PathVariable("date") @DateTimeFormat(pattern = "yyyy-MM-dd") Date date)
             {
        List<Action> actions = actionServiceImpl.getActionsByDate(date);
        return  ResponseEntity.ok(actions);
    }
    @GetMapping("/actions/{actionType}")
    public ResponseEntity<List<Action>>  getActionsByActionType(@PathVariable("actionType")Action.ActionType actionType){
        List<Action> actions=actionServiceImpl.getActionsByActionType(actionType);
        return ResponseEntity.ok(actions);
    }

}
