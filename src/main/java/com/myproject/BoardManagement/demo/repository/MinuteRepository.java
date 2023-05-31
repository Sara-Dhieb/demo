package com.myproject.BoardManagement.demo.repository;

import com.myproject.BoardManagement.demo.model.Minute;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MinuteRepository extends JpaRepository<Minute,Integer> {
}
