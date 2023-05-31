package com.myproject.BoardManagement.demo.repository;

import com.myproject.BoardManagement.demo.model.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document,Integer> {
}
