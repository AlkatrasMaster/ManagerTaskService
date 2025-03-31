package org.example.frameworks.services.serv;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.frameworks.dto.CommentDto;
import org.example.frameworks.entity.Comment;
import org.example.frameworks.entity.Task;
import org.example.frameworks.repository.CommentRepository;
import org.example.frameworks.repository.TaskRepository;
import org.example.frameworks.services.crudes.CommentCRUDServices;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServices implements CommentCRUDServices<CommentDto> {


    private final CommentRepository commentRepository;
    private final TaskRepository taskRepository;

    @Override
    public CommentDto getById(Integer id) throws ResponseStatusException {
        log.info("Получение комментария с ID: {}", id);
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Комментарий с ID %d не найден", id)));
        return mapToDo(comment);
    }

    @Override
    public List<CommentDto> findByTaskId(Integer taskId) {
        log.info("Получение всех комментариев для задачи с ID: {}", taskId);
        List<Comment> comments = commentRepository.findByTaskId(taskId);
        return comments.stream()
                .map(CommentServices::mapToDo)
                .collect(Collectors.toList());
    }

    @Override
    public CommentDto create(CommentDto commentDto) {
        log.info("Создание нового комментария для задачи с ID: {}", commentDto.getTaskId());

        Task task = taskRepository.findById(commentDto.getTaskId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Задача с ID %d не найдена", commentDto.getTaskId())));

        Comment comment = mapToEntity(commentDto);
        comment.setTask(task);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdateAt(LocalDateTime.now());

        Comment savedComment = commentRepository.save(comment);
        return mapToDo(savedComment);
    }

    @Override
    public CommentDto update(Integer id, CommentDto commentDto) {
        log.info("Обновление комментария с ID: {}", id);
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Комментарий с ID %d не найден", id)));
        comment.setText(commentDto.getText());
        comment.setUpdateAt(LocalDateTime.now());
        Comment updatedComment = commentRepository.save(comment);
        return mapToDo(updatedComment);
    }

    @Override
    public void deleteById(Integer id) throws ResponseStatusException {
        log.info("Удаление комментария с ID: {}", id);
        if (!commentRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    String.format("Комментарий с ID %d не найден", id));
        }
        commentRepository.deleteById(id);
    }

    /**
     * Преобразование сущности комментария в DTO.
     * @param comment сущность для преобразования
     * @return DTO объект комментария
     */
    public static CommentDto mapToDo(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setText(comment.getText());
        commentDto.setTaskId(comment.getTask().getId());
        commentDto.setCreatedAt(comment.getCreatedAt());
        commentDto.setUpdateAt(comment.getUpdateAt());
        return commentDto;
    }


    /**
     * Преобразование DTO в сущность комментария.
     * @param commentDto DTO для преобразования
     * @return сущность комментария
     */
    public static Comment mapToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setText(commentDto.getText());
        return comment;
    }
}
