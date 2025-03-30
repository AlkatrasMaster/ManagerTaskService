package org.example.frameworks.entity.enumes;



/**
 * TODO: Этот класс перечисление возможных приоритетов задачи.
 *  Используется для определения важности задачи в системе.
 */
public enum TaskPriority {

    /**
     * Высокий приоритет - критически важная задача
     */
    HIGH("высокий"),

    /**
     * Средний приоритет - обычная задача
     */
    MEDIUM("средний"),

    /**
     * Низкий приоритет - задача низкой важности
     */
    LOW("низкий");

    /**
     * Описание приоритета
     */
    private final String value;

    /**
     * Конструктор для инициализации значения приоритета
     */
    TaskPriority(String value) {
        this.value = value;
    }

    /**
     * Получение описания приоритета
     */
    public String getValue() {
        return value;
    }
}
