package com.myproject.BoardManagement.demo.service;

import com.myproject.BoardManagement.demo.model.Document;

public interface DocumentService {

    void addDocument(Document document);
    Document getDocument(String id);

}
