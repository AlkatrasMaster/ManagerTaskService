package org.example.frameworks.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.frameworks.dto.UserDto;
import org.example.frameworks.services.serv.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


/**
 * TODO: Контроллер для обработки HTTP-запросов, связанных с пользователями.
 *  Обеспечивает REST API для работы с пользователями в системе управления задачами.
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    /**
     * Сервисный слой для работы с пользователями.
     * Инъецируется через конструктор благодаря @RequiredArgsConstructor.
     */
    private final UserService userService;

    /**
     * Создание нового пользователя в системе.
     *
     * @param userDto Данные нового пользователя
     * @return Созданный пользователь с присвоенным ID
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto) {
        log.info("Создание нового пользователя");
        try {
            UserDto createdUser = userService.create(userDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (ResponseStatusException e) {
            throw e;
        }
    }

    /**
     * Получение пользователя по ID.
     *
     * @param id ID пользователя
     * @return Пользователь с указанным ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Integer id) {
        log.info("Получение пользователя с ID: {}", id);
        try {
            UserDto userDto = userService.getById(id);
            return ResponseEntity.ok(userDto);
        } catch (ResponseStatusException e) {
            throw e;
        }
    }

    /**
     * Получение всех пользователей в системе.
     *
     * @return Список всех пользователей
     */
    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        log.info("Получение всех пользователей");
        List<UserDto> users = userService.getALl();
        return ResponseEntity.ok(users);
    }

    /**
     * Обновление существующего пользователя.
     *
     * @param id ID пользователя для обновления
     * @param userDto Обновленные данные пользователя
     * @return Обновленный пользователь
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Integer id, @RequestBody UserDto userDto) {
        log.info("Обновление пользователя с ID: {}", id);
        try {
            userDto.setId(id);
            UserDto updatedUser = userService.update(id, userDto);
            return ResponseEntity.ok(updatedUser);
        } catch (ResponseStatusException e) {
            throw e;
        }
    }

    /**
     * Удаление пользователя из системы.
     *
     * @param id ID пользователя для удаления
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Integer id) {
        log.info("Удаление пользователя с ID: {}", id);
        try {
            userService.deleteById(id);
        } catch (ResponseStatusException e) {
            throw e;
        }
    }
}
