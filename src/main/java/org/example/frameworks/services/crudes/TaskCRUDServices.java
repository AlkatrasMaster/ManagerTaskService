package org.example.frameworks.services.crudes;

import org.springframework.web.server.ResponseStatusException;

import java.util.List;


public interface TaskCRUDServices<T> {

    T getById(Long id) throws ResponseStatusException;
    List<T> getALL();
    void create(T dto);
    void update(Long id, T dto) throws ResponseStatusException;
    void deletedById(Long id) throws ResponseStatusException;

}
