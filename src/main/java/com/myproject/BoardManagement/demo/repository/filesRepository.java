package com.myproject.BoardManagement.demo.repository;

import com.myproject.BoardManagement.demo.model.files;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface filesRepository extends JpaRepository<files,Integer> {

}
