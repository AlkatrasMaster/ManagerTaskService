package org.example.frameworks.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    /**
     * Уникальный идентификатор комментария.
     * Генерируется автоматически при создании нового комментария.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Текст комментария.
     * Содержит основную информацию, которую пользователь хочет добавить к задаче.
     */
    @Column(name = "text", nullable = false)
    private String text;

    /**
     * ID задачи, к которой относится комментарий.
     * Обеспечивает связь с сущностью Task.
     */
    @Column(name = "task_id", nullable = false)
    private Long taskId;

    /**
     * Временная метка создания комментария.
     * Устанавливается автоматически при создании и не может быть изменена.
     */
    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Временная метка последнего обновления комментария.
     * Устанавливается автоматически при создании и обновлении.
     */
    @CreationTimestamp
    @Column(name = "update_at", nullable = false)
    private LocalDateTime updateAt;
}
