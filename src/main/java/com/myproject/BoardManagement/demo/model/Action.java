package com.myproject.BoardManagement.demo.model;

import lombok.*;
import org.hibernate.annotations.Table;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
//juste l'admin qui consulte
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Entity
@Table(appliesTo="action")
public class Action {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
   private int ActionId;
    //enum
    private ActionType actionType;
    private Date date;
    public enum ActionType {
//        des actions pour un simple user et des actions pour l'administrateur
//        DES ACTIONS POUR UN SIMPLE USER
        CONNECT,
        DISCONNECT,
        CREATE_REUNION,
        UPDATE_REUNION,
        DELETE_REUNION,
        VOTE_RESOLUTION,
        ADD_DOCUMENTS,
        SIGN_DOCUMENTS,
        SEARCH_DOCUMENTS,
        WRITE_MINUTE,


//        DES ACTIONS POUR UN ADMINISTRATOR
        CREATE_RESOLUTION,
        UPDATE_RESOLUTION,
        DELETE_RESOLUTION,
        APPROVE_REUNION,
        APPROVE_MINUTE,
        CONSULT_HISTORY,
        CONSULT_MINUTES,
        OTHER,




    }

}
