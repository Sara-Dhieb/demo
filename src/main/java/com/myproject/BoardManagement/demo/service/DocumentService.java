package com.myproject.BoardManagement.demo.service;

import com.myproject.BoardManagement.demo.model.files;

public interface DocumentService {

    void addDocument( files document);
    files getDocument(String id);

}
