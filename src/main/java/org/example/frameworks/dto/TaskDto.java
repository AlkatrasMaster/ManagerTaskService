package org.example.frameworks.dto;

import lombok.*;
import org.example.frameworks.entity.enumes.TaskPriority;
import org.example.frameworks.entity.enumes.TaskStatus;

import java.time.LocalDateTime;


/**
 * TODO: Этот класс является частью архитектуры приложения и используется для
 *  передачи данных о задачах между различными слоями, обеспечивая чистоту и безопасность архитектуры.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskDto {

    private Long id; // Уникальный идентификатор задачи
    private String title; // Заголовок задачи
    private String description; // Подробное описание задачи
    private Boolean completed;
    private String status; // Статус задачи
    private String priority; // Приоритет задачи
    private LocalDateTime createdAt; // Временная метка создания задачи
    private LocalDateTime updateAt; // Временная метка последнего обновления задачи
}
