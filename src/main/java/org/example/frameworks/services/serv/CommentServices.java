package org.example.frameworks.services.serv;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.frameworks.dto.CommentDto;
import org.example.frameworks.entity.Comment;
import org.example.frameworks.repository.CommentRepository;
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

    @Override
    public CommentDto getById(Long id) throws ResponseStatusException {
        log.info("Получение комментария с ID: {}", id);
        Comment comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        String.format("Комментарий с ID %d не найден", id)));
        return mapToDo(comment);
    }

    @Override
    public List<CommentDto> findByTaskId(Long taskId) {
        log.info("Получение всех комментариев для задачи с ID: {}", taskId);
        List<Comment> comments = commentRepository.findByTaskId(taskId);
        return comments.stream()
                .map(CommentServices::mapToDo)
                .collect(Collectors.toList());
    }

    @Override
    public CommentDto create(CommentDto commentDto) {
        log.info("Создание нового комментария");
        Comment comment = mapToEntity(commentDto);
        comment.setCreatedAt(LocalDateTime.now());
        comment.setUpdateAt(LocalDateTime.now());
        Comment savedComment = commentRepository.save(comment);
        return mapToDo(savedComment);
    }

    @Override
    public CommentDto update(Long id, CommentDto commentDto) {
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
    public void deleteById(Long id) throws ResponseStatusException {
        log.info("Удаление комментария с ID: {}", id);
        if (!commentRepository.existsByUd(id)) {
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
    private static CommentDto mapToDo(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setText(comment.getText());
        commentDto.setTaskId(commentDto.getTaskId());
        commentDto.setCreatedAt(commentDto.getCreatedAt());
        commentDto.setUpdateAt(commentDto.getUpdateAt());
        return commentDto;
    }


    /**
     * Преобразование DTO в сущность комментария.
     * @param commentDto DTO для преобразования
     * @return сущность комментария
     */
    private static Comment mapToEntity(CommentDto commentDto) {
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setText(commentDto.getText());
        comment.setTaskId(commentDto.getTaskId());
        return comment;
    }
}
