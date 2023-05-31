package com.myproject.BoardManagement.demo.repository;

import com.myproject.BoardManagement.demo.model.Reunion;
import com.myproject.BoardManagement.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReunionRepository extends JpaRepository<Reunion,Integer> {

    @Query("SELECT r FROM Reunion r JOIN r.users u WHERE u.username = :username")
    List<Reunion> findByUsersUsername(String username);

    Optional<List<Reunion>> findByDateBetween(Date fromDate, Date toDate);
    Optional<List<Reunion>> findByDateAfter(Date fromDate);
}
