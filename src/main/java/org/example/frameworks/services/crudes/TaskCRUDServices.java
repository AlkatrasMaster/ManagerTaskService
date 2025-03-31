package org.example.frameworks.services.crudes;

import org.example.frameworks.dto.TaskDto;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;



/**
 * TODO: Этот интерфейс определяет контракт для работы с задачами,
 *  обеспечивая стандартизацию операций и обработку ошибок.
 *  Он используется как основа для реализации сервисного слоя приложения.
 */
public interface TaskCRUDServices<T> {

    T getById(Integer id) throws ResponseStatusException;
    List<T> getALL();
    void create(T dto);
    void update(Integer id, T dto) throws ResponseStatusException;
    void deletedById(Integer id) throws ResponseStatusException;

}
