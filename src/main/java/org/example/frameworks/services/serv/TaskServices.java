package org.example.frameworks.services.serv;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.frameworks.dto.TaskDto;
import org.example.frameworks.entity.Task;
import org.example.frameworks.repository.TaskRepository;
import org.example.frameworks.services.crudes.TaskCRUDServices;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;



/**
 * TODO: Этот класс является частью многослойной архитектуры приложения
 *  и отвечает за бизнес-логику работы с задачами, обеспечивая их создание,
 *  чтение, обновление и удаление с правильной обработкой ошибок и логированием всех операций.
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class TaskServices implements TaskCRUDServices<TaskDto> {

    private final TaskRepository taskRepository;

    /**
     * Получение задачи по ID
     * @param id идентификатор задачи
     * @return объект задачи
     * @throws ResponseStatusException если задача не найдена
     */
    @Override
    public TaskDto getById(Long id) {
        log.info("Получение задачи с ID: {}", id);
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Задача с id %d не найдена", id)));
        return null;
    }

    /**
     * Получение всех задач
     * @return список всех задач
     */
    @Override
    public List<TaskDto> getALL() {
        log.info("Получение всех задач");
        return taskRepository.findAll()
                .stream()
                .map(TaskServices::mapToDto)
                .toList();
    }

    /**
     * Создание новой задачи
     * @param taskDto объект задачи для создания
     * @throws ResponseStatusException если заголовок слишком длинный
     */
    @Override
    public void create(TaskDto taskDto) {
        log.info("Создание новой задачи");
        Task task = mapToEntity(taskDto);
        task.setCreatedAt(LocalDateTime.now());
        task.setCreatedAt(LocalDateTime.now());
        taskRepository.save(task);
    }

    /**
     * Обновление существующей задачи
     * @param id идентификатор задачи
     * @param taskDto обновленные данные задачи
     * @throws ResponseStatusException если задача не найдена или данные некорректны
     */
    @Override
    public void update(Long id, TaskDto taskDto) {
        log.info("Обновление задачи {}", id);
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Задачи не найдена"));
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setCompleted(taskDto.getCompleted());
        task.setUpdateAt(LocalDateTime.now());
        taskRepository.save(task);
    }

    /**
     * Удаление задачи по ID
     * @param id идентификатор задачи
     * @throws ResponseStatusException если задача не найдена
     */
    @Override
    public void deletedById(Long id) {
        log.info("Удаление задачи с ID: {}", id);
        if (!taskRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Задачи не найдена");
        }
        taskRepository.deleteById(id);
    }

    /**
     * Преобразование сущности задачи в DTO.
     * Копирует основные поля.
     * @param task сущность для преобразования
     * @return DTO объект задачи
     */
    public static TaskDto mapToDto(Task task) {
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setTitle(task.getTitle());
        taskDto.setDescription(task.getDescription());
        taskDto.setCompleted(task.getCompleted());
        taskDto.setCreatedAt(task.getCreatedAt());
        taskDto.setUpdateAt(task.getUpdateAt());
        return taskDto;
    }

    /**
     * Преобразование DTO в сущность задачи.
     * Копирует основные поля из DTO в сущность.
     * @param taskDto DTO для преобразования
     * @return сущность задачи
     */

    public static Task mapToEntity(TaskDto taskDto) {
        Task task = new Task();
        task.setId(taskDto.getId());
        task.setTitle(taskDto.getTitle());
        task.setDescription(taskDto.getDescription());
        task.setCompleted(taskDto.getCompleted());
        return task;
    }
}
