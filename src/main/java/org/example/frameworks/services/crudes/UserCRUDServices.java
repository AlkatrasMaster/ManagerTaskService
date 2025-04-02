package org.example.frameworks.services.crudes;

import org.springframework.web.server.ResponseStatusException;

import java.util.List;


/**
 * TODO: Интерфейс для CRUD операций с пользователями.
 *  Определяет основные методы для работы с пользователями в системе.
 */
public interface UserCRUDServices<T>{

    /**
     * Получение пользователя по ID.
     * @param id идентификатор пользователя
     * @return объект пользователя в формате DTO
     * @throws ResponseStatusException если пользователь не найден
     */
    T getById(Integer id) throws ResponseStatusException;

    /**
     * Получение всех пользователей в системе.
     * @return список всех пользователей в формате DTO
     */
    List<T> getALl();

    /**
     * Создание нового пользователя.
     * @param userDto данные пользователя для создания
     * @throws ResponseStatusException если данные некорректны
     */
    T create (T userDto) throws ResponseStatusException;

    /**
     * Обновление существующего пользователя.
     * @param id идентификатор пользователя
     * @param userDto обновленные данные пользователя
     * @throws ResponseStatusException если пользователь не найден или данные некорректны
     */
    T update(Integer id, T userDto) throws ResponseStatusException;

    /**
     * Удаление пользователя по ID.
     * @param id идентификатор пользователя
     * @throws ResponseStatusException если пользователь не найден
     */
    void deleteById(Integer id) throws ResponseStatusException;
}
