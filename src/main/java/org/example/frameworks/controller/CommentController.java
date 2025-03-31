package org.example.frameworks.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.frameworks.dto.CommentDto;
import org.example.frameworks.services.serv.CommentServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
@Slf4j
public class CommentController {

    private final CommentServices commentServices;

    /**
     * Получение комментария по ID.
     * @param id ID комментария
     * @return комментарий в формате DTO
     */
    @GetMapping("/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable Integer id) {
        log.info("Получение комментария с ID: {}", id);
        try {
            CommentDto commentDto = commentServices.getById(id);
            return ResponseEntity.ok(commentDto);
        } catch (ResponseStatusException e) {
            throw e;
        }
    }

    /**
     * Получение всех комментариев к задаче.
     * @param taskId ID задачи
     * @return список комментариев
     */

    @GetMapping("/tasks/{taskId}")
    public ResponseEntity<List<CommentDto>> getCommentsByTaskId(@PathVariable Integer taskId) {
        log.info("Получение всех комментариев для задачи с ID: {}", taskId);
        List<CommentDto> comments = commentServices.findByTaskId(taskId);
        return ResponseEntity.ok(comments);
    }

    /**
     * Создание нового комментария.
     * @param commentDto данные комментария
     * @return созданный комментарий
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto) {
        log.info("Создание нового комментария");
        try {
            CommentDto createdComment = commentServices.create(commentDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdComment);
        } catch (ResponseStatusException e) {
            throw e;
        }
    }

    /**
     * Обновление существующего комментария.
     * @param id ID комментария
     * @param commentDto обновленные данные
     * @return обновленный комментарий
     */

    @PutMapping("/{id}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Integer id, @RequestBody CommentDto commentDto) {
        log.info("Обновление комментария с ID: {}", id);
        try {
            commentDto.setId(id);
            CommentDto updateComment = commentServices.update(id, commentDto);
            return ResponseEntity.ok(updateComment);
        } catch (ResponseStatusException e) {
            throw e;
        }
    }

    /**
     * Удаление комментария.
     * @param id ID комментария
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable Integer id) {
        log.info("Удаление комментария с ID: {}", id);
        try {
            commentServices.deleteById(id);
        } catch (ResponseStatusException e) {
            throw e;
        }
    }
}
