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


    private Integer id; // Уникальный идентификатор комментария.
    private String text; // Текст комментария.
    private Integer taskId; // ID задачи, к которой относится комментарий.
    private LocalDateTime createdAt; // Временная метка создания комментария.
    private LocalDateTime updateAt; // Временная метка последнего обновления комментария.


}
