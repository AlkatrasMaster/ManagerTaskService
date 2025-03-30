package org.example.frameworks.services.crudes;

import org.springframework.web.server.ResponseStatusException;

import java.util.List;

/**
 * TODO: Этот Интерфейс для CRUD операций с комментариями.
 *  Определяет основные методы для работы с комментариями к задачам.
 */
public interface CommentCRUDServices<T>{


    /**
     * Получение комментария по ID.
     * @param id идентификатор комментария
     * @return комментарий в формате DTO
     * @throws ResponseStatusException если комментарий не найден
     */
    T getById(Long id) throws ResponseStatusException;

    /**
     * Получение всех комментариев по ID задачи.
     * @param taskId ID задачи
     * @return список комментариев в формате DTO
     */
    List<T> findByTaskId(Long taskId);

    /**
     * Создание нового комментария.
     * @param commentDto данные комментария для создания
     * @return созданный комментарий в формате DTO
     */
    T create(T commentDto);

    /**
     * Обновление существующего комментария.
     * @param id ID комментария
     * @param commentDto обновленные данные комментария
     * @return обновленный комментарий в формате DTO
     * @throws ResponseStatusException если комментарий не найден
     */
    T update(Long id, T commentDto);


    /**
     * Удаление комментария по ID.
     * @param id ID комментария
     * @throws ResponseStatusException если комментарий не найден
     */
    void deleteById(Long id) throws ResponseStatusException;

}
