package com.myproject.BoardManagement.demo.repository;

import com.myproject.BoardManagement.demo.model.Action;
import com.myproject.BoardManagement.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
        List<User> findByUsernameContaining(String searchString);
        User findByUsername(String username);
        String findEmailByUsername(String username);
        String findFirstnameByUsername(String username);
        String findLastNameByUsername(String username);

    String findRoleByUsername(String username);



}

