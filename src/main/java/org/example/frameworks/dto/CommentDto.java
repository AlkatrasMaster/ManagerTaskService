package org.example.frameworks.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentDto {

    /**
     * Уникальный идентификатор комментария.
     */
    private Integer id;

    /**
     * Текст комментария.
     */
    private String text;

    /**
     * ID задачи, к которой относится комментарий.
     */
    private Integer taskId;

    /**
     * Временная метка создания комментария.
     */
    private LocalDateTime createdAt;

    /**
     * Временная метка последнего обновления комментария.
     */
    private LocalDateTime updateAt;


}
