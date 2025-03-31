package org.example.frameworks.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.frameworks.dto.TaskDto;
import org.example.frameworks.services.serv.TaskServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
/**
 * TODO: Это REST-контроллер, который обрабатывает HTTP-запросы для работы с задачами.
 */


@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
@Slf4j
public class TaskController {

    private final TaskServices taskServices;

    /**
     * Получение задачи по идентификатору
     * @param id идентификатор задачи
     * @return объект задачи в формате DTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getTaskById(@PathVariable Integer id) {
        log.info("Получение задачи с ID: {}", id);
        try {
            TaskDto taskDto = taskServices.getById(id);
            return ResponseEntity.ok(taskDto);
        } catch (ResponseStatusException e) {
            throw e;
        }
    }

    /**
     * Получение всех задач
     * @return Список всех задач в формате DTO
     */
    @GetMapping
    public ResponseEntity<List<TaskDto>> getAllTasks() {
        log.info("Получение всех задач");
        List<TaskDto> tasks = taskServices.getALL();
        return ResponseEntity.ok(tasks);
    }

    /**
     * Создание новой задачи
     * @param taskDto Объект задачи для создания
     * @return Созданная задача
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto) {
        log.info("Создание новой задачи");
        try {
            taskServices.create(taskDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(taskDto);
        } catch (ResponseStatusException e) {
            throw e;
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskDto> updateTask(@PathVariable Integer id, @RequestBody TaskDto taskDto) {
        log.info("Обновление задачи с ID: {}", id);
        try {
            taskServices.update(id, taskDto);
            return ResponseEntity.ok(taskDto);
        } catch (ResponseStatusException e) {
            throw e;
        }
    }

    /**
     * Удаление задачи по идентификатору
     * @param id Идентификатор задачи для удаления
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable Integer id) {
        log.info("Удаление задачи с ID: {}", id);
        try {
            taskServices.deletedById(id);
        } catch (ResponseStatusException e) {
            throw e;
        }
    }
}
