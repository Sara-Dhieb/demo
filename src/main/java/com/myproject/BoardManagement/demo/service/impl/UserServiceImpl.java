package com.myproject.BoardManagement.demo.service.impl;

import com.myproject.BoardManagement.demo.dto.UserDto;
import com.myproject.BoardManagement.demo.model.Action;
import com.myproject.BoardManagement.demo.model.Reunion;
import com.myproject.BoardManagement.demo.model.User;
import com.myproject.BoardManagement.demo.repository.ActionRepository;
import com.myproject.BoardManagement.demo.repository.UserRepository;
import com.myproject.BoardManagement.demo.service.UserService;
import com.myproject.BoardManagement.demo.service.keycloak.KeycloakUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    ActionRepository actionRepository;
    @Autowired
    KeycloakUserService keycloakUserService;

    ModelMapper modelMapper = new ModelMapper();


    //        @Autowired
//        KeyCloakService keyCloakService;
    @Override
    public User getUserById(int id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        }
        else
            return null;
    };
    @Override
    public User saveUser(User user) {
        List<Action> actionList = new ArrayList<>();
        if (user.getAction() != null && !user.getAction().isEmpty()) {
            user.getAction().forEach(action -> {
                        Action.ActionType actionType = action.getActionType();
//                        treating the exception where actionType not of Type Action.ActionType

//                        for (Action.ActionType validActionType : Action.ActionType.values()) {
//                            if (!validActionType.equals(actionType)) {
//                                actionType = Action.ActionType.OTHER;
//                            }
//                        }
                        action.setActionType(actionType);
                        actionList.add(action);

                    }


            );
        }
//        password and department aren't present that's why not saved in the database
        User user1 = User.builder()
                .username(user.getUsername())
                .action(actionList)
                .lastName(user.getLastName())
                .firstname(user.getFirstname())
                .email(user.getEmail())
                .role(user.getRole())
                .build();

        return userRepository.save(user1);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

//    public User findUserByUsername(String username) {
//
//        Optional<User> user = Optional.ofNullable(userRepository.findByUsername(username));
//        if (user.isPresent()) {
//            return user.get();
//        } else
//            return null;
//    }


    public List<User> getUsersByUsernameContaining(String searchString) {
        return userRepository.findByUsernameContaining(searchString);

    }

    @Override
    public List<Action> findUserActions(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return user.getAction();
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }

    @Override
    public List<Action> getActionsByUsernameAndDate(String username, Date date) {
        User user = userRepository.findByUsername(username);
        List<Action> actionDates = new ArrayList<>();
        if (user != null) {
            List<Action> actions = user.getAction();
            for (Action action : actions) {
                if (action.getDate().equals(date)) {
                    actionDates.add(action);
                }
            }
        }
        return actionDates;
    }

    @Override
    public User addUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        keycloakUserService.createUser(userDto);
        return saveUser(user);
    }


}

