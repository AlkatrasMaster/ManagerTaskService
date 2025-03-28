package org.example.frameworks.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.frameworks.entity.enumes.TaskStatus;

import java.time.LocalDateTime;


/**
 * TODO: Этот класс является частью архитектуры приложения и используется для хранения
 *  данных о задачах в базе данных, обеспечивая их структурированное хранение и доступ к ним через JPA.
 */
@Entity
@Table(name = "task")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task {

    /**
     * Уникальный идентификатор задачи.
     * Генерируется автоматически при создании новой задачи.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Заголовок задачи.
     * Краткое описание сути задачи.
     */
    @Column(name = "title")
    private String title;

    /**
     * Подробное описание задачи.
     * Может содержать детали реализации и дополнительную информацию.
     */
    @Column(name = "description")
    private String description;


    /**
     * Статус выполнения задачи.
     * По умолчанию false (задача не выполнена).
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TaskStatus status;


    /**
     * Временная метка создания задачи.
     * Устанавливается автоматически при создании и не может быть изменена.
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Временная метка последнего обновления задачи.
     * Устанавливается автоматически при создании и обновлении.
     */
    @Column(name = "update_at", nullable = false)
    private LocalDateTime updateAt;
}
