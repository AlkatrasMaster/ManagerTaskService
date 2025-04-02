package org.example.frameworks.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    /**
     * Уникальный идентификатор пользователя.
     * Генерируется автоматически при создании нового пользователя.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * Имя пользователя в системе.
     * Должно быть уникальным.
     */
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    /**
     * Email пользователя.
     * Должен быть уникальным.
     */
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    /**
     * Пароль пользователя.
     * Хранится в зашифрованном виде.
     */
    @Column(name = "email", nullable = false, unique = true)
    private String password;

    /**
     * Временная метка создания пользователя.
     * Устанавливается автоматически при создании и не может быть изменена.
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * Временная метка последнего обновления пользователя.
     * Устанавливается автоматически при создании и обновлении.
     */
    @Column(name = "update_at", nullable = false)
    private LocalDateTime updatedAt;
}
